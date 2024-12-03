package org.lab3;

import org.lab3.annotations.JsonField;
import org.lab3.annotations.SerializedToJson;

/**
 * A model class representing a Dog. It is annotated with the @SerializedToJson annotation to specify
 * that it should be serialized into JSON format.
 */
@SerializedToJson
public class Dog {
    @JsonField(name = "name")
    private String name;
    @JsonField(name = "age")
    private int age;
    @JsonField(name = "fur_color")
    private String furColor;
    @JsonField(name = "weight")
    private int weight;

    public Dog(int weight, String furColor, int age, String name) {
        this.weight = weight;
        this.furColor = furColor;
        this.age = age;
        this.name = name;
    }
}