package org.lab3.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a class for JSON serialization.
 * This annotation is processed at compile time to generate a JSON serializer for the annotated class.
 * Fields intended for JSON serialization must be annotated with {@link JsonField}.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface SerializedToJson {
}
