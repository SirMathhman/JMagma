package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Function;

import static com.meti.api.core.None.*;
import static com.meti.api.core.Some.*;

public class NIOPath implements Path {
    private final java.nio.file.Path root;

    public NIOPath(java.nio.file.Path root) {
        this.root = root;
    }

    @Override
    public Directory createDirectory() throws IOException {
        Files.createDirectory(root);
        return new NIODirectory(root);
    }

    @Override
    public Extant createFile() throws IOException {
        Files.createFile(root);
        return new NIOExtant(root);
    }

    @Override
    public boolean isExtinct() {
        return !Files.exists(root);
    }

    @Override
    public Directory asDirectory() {
        return new NIODirectory(root);
    }

    @Override
    public File<Extant> asFile() {
        return isExtant() ?
                new NIOExtant(root) :
                new NIOExtinct(root);
    }

    @Override
    public boolean isExtant() {
        return Files.exists(root);
    }

    @Override
    public <T> T ensuringExistenceAsFile(Function<Extant, T> mapper) throws IOException {
        return mapper.apply(asFile().ensure());
    }

    @Override
    public <T> Option<T> mapExistenceAsFile(Function<Extant, T> mapper) {
        return isExtant() ? Some(mapper.apply(new NIOExtant(root))) : None();
    }

    @Override
    public <T> T ensuringExistenceAsDirectory(Function<Directory, T> mapper) throws IOException {
        Directory directory = isExtant() ? asDirectory() : createDirectory();
        return mapper.apply(directory);
    }
}
