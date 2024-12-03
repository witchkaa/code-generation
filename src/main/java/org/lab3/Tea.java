package org.lab3;


import org.lab3.annotations.SerializedToXml;
import org.lab3.annotations.XmlField;

/**
 * A model class representing a Tea. It is annotated with the @SerializedToXml annotation to specify
 * that it should be serialized into XML format.
 */
@SerializedToXml
public class Tea {
    @XmlField(name = "cup_size")
    private String cupSize;
    @XmlField(name = "type")
    private String type;
    @XmlField(name = "price")
    private double price;

    public Tea(String type, double price, String cupSize) {
        this.type = type;
        this.price = price;
        this.cupSize = cupSize;
    }
}