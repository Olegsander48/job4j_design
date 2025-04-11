package ru.job4j.cache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) throws FileNotFoundException {
        this.cachingDir = cachingDir;
        checkDirectoryForExisting(Path.of(cachingDir));
    }

    @Override
    protected String load(String key) throws FileNotFoundException {
        String line;
        Path fullPathToFile = Path.of(cachingDir + "\\" + key);
        checkDirectoryForExisting(fullPathToFile);
        try {
            line = Files.readString(fullPathToFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        put(key, line);
        return line;
    }

    private void checkDirectoryForExisting(Path path) throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Can't find such directory or file: " + path);
        }
    }
}
