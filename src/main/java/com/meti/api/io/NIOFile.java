package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOFile implements File {
    private final java.nio.file.Path path;

    public NIOFile(Path path) {
        this.path = path;
    }

    @Override
    public String readAsString() throws IOException {
        return Files.readString(path);
    }
}
