package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(false, 7000, "Mercedes", new Engine(2.2, 170, 400),
                new String[] {"heated seats", "heated mirrors"});

        /** Преобразуем объект Car в json-строку. */
        final Gson gson = new GsonBuilder().create();
        String carJson = gson.toJson(car);
        System.out.println(carJson);

        /** Преобразуем объект Json в объект Car. */
        final Car carFromJson = gson.fromJson(carJson, Car.class);
        System.out.println(carFromJson);

    }
}
