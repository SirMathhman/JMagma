package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOExtinct implements Extinct {
    private final Path path;

    public NIOExtinct(Path path) {
        this.path = path;
    }

    @Override
    public Extant ensure() throws IOException {
        if (!Files.exists(path)) Files.createFile(path);
        return new NIOExtant(path);
    }
}
