package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        map.merge(new FileProperty(
                file.toFile().length(), file.getFileName().toString()),
                new ArrayList<>(List.of(file.toAbsolutePath())),
                (oldV, newV) -> {
                    oldV.add(file);
                    return oldV;
                });
        return super.visitFile(file, attributes);
    }

    public void printValues() {
        map.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .flatMap(entry -> entry.getValue().stream())
                .forEach(System.out::println);
    }
}
