package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private String brand;

    @XmlAttribute
    private int age;
    private Engine engine;

    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private String[] options;

    public Car() {
    }

    public Car(String brand, int age, Engine engine, String[] options) {
        this.brand = brand;
        this.age = age;
        this.engine = engine;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car{"
                + "brand='" + brand + '\''
                + ", age=" + age
                + ", engine=" + engine
                + ", options=" + Arrays.toString(options)
                + '}';
    }
}
