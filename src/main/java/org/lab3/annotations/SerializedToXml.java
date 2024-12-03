package org.lab3.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a class for XML serialization.
 * This annotation is processed at compile time to generate an XML serializer for the annotated class.
 * Fields intended for XML serialization must be annotated with {@link XmlField}.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface SerializedToXml {
}
