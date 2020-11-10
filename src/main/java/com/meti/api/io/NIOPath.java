package com.meti.api.io;

import java.io.IOException;
import java.nio.file.Files;

public class NIOPath implements Path {
    private final java.nio.file.Path root;

    public NIOPath(java.nio.file.Path root) {
        this.root = root;
    }

    @Override
    public void delete() throws IOException {
        Files.delete(getRoot());
    }

    @Override
    public void write(CharSequence output) throws IOException {
        Files.writeString(getRoot(), output);
    }

    @Override
    public Directory createDirectory() throws IOException {
        Files.createDirectory(getRoot());
        return new NIODirectory(getRoot());
    }

    @Override
    public File createFile() throws IOException {
        Files.createFile(getRoot());
        return new NIOFile(this.getRoot());
    }

    @Override
    public boolean isExtinct() {
        return !Files.exists(getRoot());
    }

    @Override
    @Deprecated
    public Path resolve(String name) {
        return new NIOPath(root.resolve(name));
    }

    @Override
    public java.nio.file.Path getRoot() {
        return root;
    }

    @Override
    public Directory asDirectory() {
        return new NIODirectory(getRoot());
    }

    @Override
    public File asFile() {
        return new NIOFile(this.getRoot());
    }

    @Override
    public boolean isExtant() {
        return Files.exists(root);
    }
}
