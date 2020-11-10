package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;

public class Path {
    private final java.nio.file.Path root;

    public Path(java.nio.file.Path root) {
        this.root = root;
    }

    public void delete() throws IOException {
        Files.delete(getRoot());
    }

    public void write(CharSequence output) throws IOException {
        Files.writeString(getRoot(), output);
    }

    public Directory createDirectory() throws IOException {
        Files.createDirectory(getRoot());
        return new Directory(this);
    }

    public File createFile() throws IOException {
        Files.createFile(getRoot());
        return new NIOFile(this.getRoot());
    }

    public boolean isExtinct() {
        return !Files.exists(getRoot());
    }

    public Path resolve(String name) {
        return new Path(root.resolve(name));
    }

    public java.nio.file.Path getRoot() {
        return root;
    }

    public Directory asDirectory() {
        return new Directory(this);
    }

    public File asFile() {
        return new NIOFile(this.getRoot());
    }

    public boolean isExtant() {
        return Files.exists(root);
    }
}
