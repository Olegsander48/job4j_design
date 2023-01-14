package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "Car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean manual;
    @XmlAttribute
    private int price;
    @XmlAttribute
    private String brand;
    private Engine engine;
    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private String[] options;

    public Car() {
    }

    public Car(boolean manual, int price, String brand, Engine engine, String[] options) {
        this.manual = manual;
        this.price = price;
        this.brand = brand;
        this.engine = engine;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car {"
                + "manual transmission = " + manual
                + ", price = " + price
                + ", brand = " + brand
                + ", engine = " + engine
                + ", options = " + Arrays.toString(options)
                + '}';
    }
}
