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

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
