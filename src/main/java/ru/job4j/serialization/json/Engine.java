package ru.job4j.serialization.json;

public class Engine {
    final double capacity;
    final int power;
    final int torque;

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
