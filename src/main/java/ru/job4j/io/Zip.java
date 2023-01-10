package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkParams(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Directory for archived, excluded files and output archive is null.");
        }

        ArgsName argsName = ArgsName.of(args);
        File file = new File(argsName.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Directory not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }

        if (argsName.get("e").length() < 2 || !argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("File extension has invalid form. Please, usage .FILE_EXTENSION ");
        }

        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Not zip-archive %s", argsName.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        checkParams(args);
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);

        List<Path> listOfPaths = Search.search(
                Path.of(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e"))
        );

        zip.packFiles(
                listOfPaths,
                Path.of(argsName.get("o")).toFile()
        );
    }
}
