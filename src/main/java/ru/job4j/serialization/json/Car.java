package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean manual;
    private final int price;
    private final String brand;
    private final Engine engine;
    private final String[] options;

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
