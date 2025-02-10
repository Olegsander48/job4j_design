package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/multiplicationTable.txt")) {
            for (int i = 0; i <= 9; i++) {
                for (int j = 0; j <= 9; j++) {
                    output.write((j + " * " + i + " = " + i * j).getBytes());
                    output.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
