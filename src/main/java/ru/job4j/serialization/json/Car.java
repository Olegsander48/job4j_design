package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Car {
    private final boolean electric;
    private final int age;
    private final Engine engine;

    public Car(boolean electric, int age, Engine engine) {
        this.electric = electric;
        this.age = age;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{"
                + "electric=" + electric
                + ", age=" + age
                + ", engine=" + engine
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(false, 2006, new Engine(612));

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        /** Создаём новую json-строку с модифицированными данными */
        final String carJson =
                "{"
                        + "\"electric\":false,"
                        + "\"age\":2006,"
                        + "\"engine\":"
                        + "{"
                        + "\"horsePower\":\"612\""
                        + "}"
                + "}";
        /** Превращаем json-строку обратно в объект */
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
