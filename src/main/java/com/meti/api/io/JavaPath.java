package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaPath implements Path {
    private static final JavaPath Root = new JavaPath();
    private final java.nio.file.Path path;

    public JavaPath() {
        this(Paths.get("."));
    }

    private JavaPath(java.nio.file.Path path) {
        this.path = path;
    }

    public static JavaPath Root() {
        return Root;
    }

    @Override
    public Path resolveName(String name) {
        return resolvePath(new JavaPath(Paths.get(name)));
    }

    @Override
    public Path resolvePath(Path other) {
        return new JavaPath(path.resolve(other.toString()));
    }

    @Override
    public String toString() {
        return path.toString();
    }

    @Override
    public boolean exists() {
        return Files.exists(path);
    }

    @Override
    public void createAsFile() throws com.meti.api.io.IOException {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new com.meti.api.io.IOException(e);
        }
    }
}
