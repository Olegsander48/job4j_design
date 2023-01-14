package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {
    @XmlAttribute
    private Car car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }
}
