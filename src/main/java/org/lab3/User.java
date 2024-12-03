package org.lab3;

import org.lab3.annotations.JsonField;
import org.lab3.annotations.SerializedToJson;

/**
 * A model class representing a User. It is annotated with the @SerializedToJson annotation to specify
 * that it should be serialized into JSON format.
 */

@SerializedToJson
public class User {
    @JsonField(name = "username")
    private String name;

    @JsonField(name = "email")
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}