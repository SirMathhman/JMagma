package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;
import java.util.function.Function;

public interface Path {
    Directory createDirectory() throws IOException;

    Extant createFile() throws IOException;

    Directory asDirectory();

    File<Extant> asFile();

    boolean isExtant();

    <T> T ensuringExistenceAsFile(Function<Extant, T> mapper) throws IOException;

    <T> Option<T> mapExistenceAsFile(Function<Extant, T> mapper);

    <T> T ensuringExistenceAsDirectory(Function<Directory, T> mapper) throws IOException;
}
