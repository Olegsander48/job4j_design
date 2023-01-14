package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {

    @XmlAttribute
    private double capacity;
    @XmlAttribute
    private int power;
    @XmlAttribute
    private int torque;

    public Engine() {
    }

    public Engine(double volume, int power, int torque) {
        this.capacity = volume;
        this.power = power;
        this.torque = torque;
    }

    @Override
    public String toString() {
        return "Engine {"
                + "capacity = " + capacity
                + ", power = " + power
                + ", torque = " + torque
                + '}';
    }
}
