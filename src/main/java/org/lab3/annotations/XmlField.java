package org.lab3.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a field for XML serialization.
 * This annotation should be applied to fields within a class annotated with {@link SerializedToXml}.
 * The optional `name` parameter specifies the XML element name for the field.
 * If no name is provided, the field's name will be used as the XML element name.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface XmlField {
    /**
     * The name of the XML element for the annotated field.
     *
     * @return the custom name for the XML element; defaults to the field's name if not specified.
     */
    String name() default "";
}