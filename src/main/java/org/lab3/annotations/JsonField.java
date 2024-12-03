package org.lab3.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a field for JSON serialization.
 * This annotation should be applied to fields within a class annotated with {@link SerializedToJson}.
 * The optional `name` parameter specifies the JSON property name for the field.
 * If no name is provided, the field's name will be used as the JSON property name.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface JsonField {
    /**
     * The name of the JSON property for the annotated field.
     *
     * @return the custom name for the JSON property; defaults to the field's name if not specified.
     */
    String name() default "";
}