package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        short year = 2023;
        long timeInSeconds = 69420;
        float usdCourse = 2.65f;
        double euroCourse = 2.76;
        boolean goodMood = true;
        byte currentDate = 12;
        char letterA = 'A';

        LOG.debug("User info age : {}, year : {}, timeInSeconds : {}, usdCourse : {}, euroCourse : {}, goodMood : {}, currentDate : {}, letterA : {}", age, year, timeInSeconds, usdCourse, euroCourse, goodMood, currentDate, letterA);
    }
}
