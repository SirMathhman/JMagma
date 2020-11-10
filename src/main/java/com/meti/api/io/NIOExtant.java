package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOExtant implements Extant {
    private final Path path;

    public NIOExtant(Path path) {
        this.path = path;
    }

    @Override
    public String readAsString() throws IOException {
        return Files.readString(path);
    }

    @Override
    public Extinct delete() throws IOException {
        Files.delete(path);
        return new NIOExtinct(path);
    }

    @Override
    public void write(CharSequence output) throws IOException {
        Files.writeString(path, output);
    }
}
