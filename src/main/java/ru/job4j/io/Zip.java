package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArguments(ArgsName jvm) {
        if (jvm.getMapSize() != 3) {
            throw new IllegalArgumentException("Invalid number of arguments. Expected 3, but was " + jvm.getMapSize());
        }
        if (!jvm.get("e").startsWith(".") || jvm.get("e").length() < 2) {
            throw new IllegalArgumentException("Invalid format of file extension. Use .FILE_EXTENSION");
        }
        if (!jvm.get("o").toLowerCase().endsWith(".zip".toLowerCase())) {
            throw new IllegalArgumentException("Invalid format of archive extension. Use .zip");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName jvm = ArgsName.of(args);
        validateArguments(jvm);
        zip.packFiles(
                Search.search(
                        Paths.get(jvm.get("d")),
                        path -> !path.toString()
                                .endsWith(jvm.get("e"))),
                Paths.get(jvm.get("o"))
                        .toFile());
    }
}
