package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input the size of multiplication table: ");
            int size = scanner.nextInt();
            scanner.close();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    out.write(((i + 1) * (j + 1) + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
