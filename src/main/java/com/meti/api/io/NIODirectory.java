package com.meti.api.io;

import java.nio.file.Path;

public class NIODirectory implements Directory {
    private final Path value;

    public NIODirectory(Path value) {
        this.value = value;
    }

    @Override
    public File resolveFile(String name) {
        return new NIOFile(value.resolve(name));
    }

    @Override
    public Directory resolveDirectory(String name) {
        return new NIODirectory(value.resolve(name));
    }

    @Override
    public com.meti.api.io.Path resolve(String name) {
        return new NIOPath(value.resolve(name));
    }
}
