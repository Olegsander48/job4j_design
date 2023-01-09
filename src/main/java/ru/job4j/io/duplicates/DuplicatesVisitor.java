package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        duplicates.putIfAbsent(fileProperty, new ArrayList<>());
        duplicates.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }

    public void showDuplicates() {
        duplicates.entrySet()
                .stream()
                .filter(filePropertyListEntry -> filePropertyListEntry
                        .getValue()
                        .size() > 1)
                .forEach(filePropertyListEntry -> filePropertyListEntry
                        .getValue()
                        .forEach(System.out::println));
    }
}
