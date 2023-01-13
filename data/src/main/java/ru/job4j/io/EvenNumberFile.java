package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            for (String str : text.toString().split(System.lineSeparator())) {
                if (Integer.parseInt(str) % 2 == 0) {
                    System.out.println("Number " + Integer.parseInt(str) + " is even");
                } else {
                    System.out.println("Number " + Integer.parseInt(str) + " is odd");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
