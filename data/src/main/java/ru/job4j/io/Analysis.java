package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> times = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String startTime = null;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                /*start*/
                if (startTime == null && (line.contains("400") || line.contains("500"))) {
                    startTime = line.substring(line.indexOf(" ") + 1);
                }
                /*end*/
                if (startTime != null && (line.contains("200") || line.contains("300"))) {
                    times.add(startTime + ";" + line.substring(line.indexOf(" ") + 1) + ";");
                    startTime = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (String time : times) {
                out.write(time + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.log", "target.csv");
    }
}
