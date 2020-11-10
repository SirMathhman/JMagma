package com.meti.api.io;

public class Directory {
    private final Path value;

    public Directory(Path value) {
        this.value = value;
    }

    public Path getValue() {
        return value;
    }
}
