package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Aliaksandr Pryhodzich";
        int age = 50;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte day = 24;
        short month = 8;
        int year = 2077;
        long size = 67890L;
        double price = 1789456.99D;
        float rateExchange = 3.255f;
        boolean flag = true;
        char dollar = '$';
        LOG.error("Date : {}.{}.{}; Size : {}, price: {}; Rate exchange {} for {}, worth buying: {}",
                day, month, year, size, price, rateExchange, dollar, flag);
    }
}
