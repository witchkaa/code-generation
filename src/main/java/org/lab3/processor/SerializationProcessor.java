package org.lab3.processor;

import org.lab3.annotations.JsonField;
import org.lab3.annotations.SerializedToJson;
import org.lab3.annotations.SerializedToXml;
import org.lab3.annotations.XmlField;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.tools.JavaFileObject;

/**
 * This annotation processor generates JSON and XML serializers for classes
 * annotated with @SerializedToJson and @SerializedToXml, respectively.
 * It scans fields within the annotated class for additional annotations
 * (@JsonField and @XmlField) to determine how to serialize fields.
 */

@SupportedAnnotationTypes({
        "org.lab3.annotations.SerializedToJson",
        "org.lab3.annotations.SerializedToXml"
})
@SupportedSourceVersion(SourceVersion.RELEASE_18)
public class SerializationProcessor extends AbstractProcessor {
    private String className;
    private String packageName;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(SerializedToJson.class)) {
            processElement(element, JsonField.class, "Json");
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(SerializedToXml.class)) {
            processElement(element, XmlField.class, "Xml");
        }

        return true;
    }

    /**
     * Processes a single class element to generate a serializer.
     *
     * @param element The class element to process.
     * @param fieldAnnotation The field-level annotation to look for.
     * @param serializerType The type of serializer to generate ("Json" or "Xml").
     */
    private void processElement(Element element, Class<?> fieldAnnotation, String serializerType) {
        if (element.getKind() != ElementKind.CLASS) return;

        TypeElement classElement = (TypeElement) element;
        List<VariableElement> serializedFields = new ArrayList<>();

        for (Element enclosedElement : classElement.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.FIELD &&
                    enclosedElement.getAnnotation((Class) fieldAnnotation) != null) {
                serializedFields.add((VariableElement) enclosedElement);
            }
        }

        if (!serializedFields.isEmpty()) {
            className = classElement.getSimpleName().toString();
            packageName = processingEnv.getElementUtils().getPackageOf(classElement).toString();
            if ("Json".equals(serializerType)) {
                generateJsonSerializer(serializedFields);
            } else if ("Xml".equals(serializerType)) {
                generateXmlSerializer(serializedFields);
            }
        }
    }

    /**
     * Generates a JSON serializer for the annotated class.
     *
     * @param serializedFields The fields to include in the JSON serialization.
     */
    private void generateJsonSerializer(List<VariableElement> serializedFields) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packageName).append(";\n");
        sb.append("\n");
        sb.append("import java.lang.reflect.Field;\n");
        sb.append("\n");
        sb.append("public class ").append(className).append("JsonSerializer {\n");
        sb.append("    public String serialize(").append(className).append(" obj) {\n");
        sb.append("        StringBuilder json = new StringBuilder(\"{\");\n");
        sb.append("        try {\n");
        for (VariableElement field : serializedFields) {
            String fieldName = field.getAnnotation(JsonField.class).name();
            fieldName = fieldName.isEmpty() ? field.getSimpleName().toString() : fieldName;
            sb.append("            json.append(\"\\\"").append(fieldName).append("\\\": \\\"\" + obj.")
                    .append(field.getSimpleName()).append(" + \"\\\",\");\n");
        }
        sb.append("            if (json.charAt(json.length() - 1) == ',') json.setLength(json.length() - 1);\n");
        sb.append("            json.append(\"}\");\n");
        sb.append("        } catch (Exception e) {\n");
        sb.append("            e.printStackTrace();\n");
        sb.append("        }\n");
        sb.append("        return json.toString();\n");
        sb.append("    }\n");
        sb.append("}\n");

        writeToFile(sb, "JsonSerializer");
    }

    /**
     * Generates an XML serializer for the annotated class.
     *
     * @param serializedFields The fields to include in the XML serialization.
     */
    private void generateXmlSerializer(List<VariableElement> serializedFields) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packageName).append(";\n");
        sb.append("import java.lang.reflect.*;\n");
        sb.append("\n");
        sb.append("public class ").append(className).append("XmlSerializer {\n");
        sb.append("    public String serialize(").append(className).append(" obj) {\n");
        sb.append("        StringBuilder xml = new StringBuilder(\"<").append(className).append(">\");\n");
        sb.append("        try {\n");
        for (VariableElement field : serializedFields) {
            String fieldName = field.getAnnotation(XmlField.class).name();
            fieldName = fieldName.isEmpty() ? field.getSimpleName().toString() : fieldName;
            sb.append("            xml.append(\"<").append(fieldName).append(">\")\n");
            sb.append("               .append(obj.").append(field.getSimpleName()).append(")\n");
            sb.append("               .append(\"</").append(fieldName).append(">\");\n");
        }
        sb.append("            xml.append(\"</").append(className).append(">\");\n");
        sb.append("        } catch (Exception e) {\n");
        sb.append("            e.printStackTrace();\n");
        sb.append("        }\n");
        sb.append("        return xml.toString();\n");
        sb.append("    }\n");
        sb.append("}\n");

        writeToFile(sb, "XmlSerializer");
    }

    /**
     * Writes the generated source code to a file.
     *
     * @param sb The source code to write.
     * @param suffix The suffix for the class name (e.g., "JsonSerializer" or "XmlSerializer").
     */
    private void writeToFile(StringBuilder sb, String suffix) {
        try {
            JavaFileObject sourceFile = processingEnv.getFiler()
                    .createSourceFile(packageName + "." + className + suffix);
            try (Writer writer = sourceFile.openWriter()) {
                writer.write(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
