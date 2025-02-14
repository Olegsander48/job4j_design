package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class CarToXML {
    public static void main(String[] args) throws Exception {
        Car car = new Car("Mercedes", 2006, new Engine(5.5, 612),
                new String[]{"Panoramic roof", "Hydraulic suspension"});
        /** Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Car.class);
        /** Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /** Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /** Сериализуем */
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /** Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /** десериализуем */
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        /** JSONObject из json-строки строки */
        JSONObject jsonEngine = new JSONObject("{\"engine volume\":\"5.5\", \"horsePower\":\"612\"}");

        /** JSONArray из ArrayList */
        String[] options = {"Panoramic roof", "Hydraulic suspension"};
        JSONArray jsonOptions = new JSONArray(options);

        /** JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("brand", car.getBrand());
        jsonObject.put("age", car.getAge());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("options", jsonOptions);

        /** Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /** Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
