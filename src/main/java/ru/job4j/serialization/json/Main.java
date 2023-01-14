package ru.job4j.serialization.json;

import org.json.JSONObject;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {

        /* JSONObject напрямую методом put */
        final Car car = new Car(false, 7000, "Mercedes", new Engine(2.2, 170, 400),
                new String[] {"heated seats", "heated mirrors"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("manual", car.isManual());
        jsonObject.put("price", car.getPrice());
        jsonObject.put("brand", car.getBrand());
        jsonObject.put("engine", car.getEngine());
        jsonObject.put("options", car.getOptions());

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());

    }
}
