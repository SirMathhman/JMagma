package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathSource implements Source{
    private final Path path;

    public PathSource(Path path) {
        this.path = path;
    }

    @Override
    public String read() throws SourceException {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            String format = "Failed to read content from: %s";
            String message = String.format(format, path.toAbsolutePath());
            throw new SourceException(message, e);
        }
    }

    @Override
    public void write(String value) throws SourceException {
        try {
            Files.writeString(path, value);
        } catch (IOException e) {
            String format = "Failed to write content to: %s";
            String message = String.format(format, path.toAbsolutePath());
            throw new SourceException(message, e);
        }
    }
}
