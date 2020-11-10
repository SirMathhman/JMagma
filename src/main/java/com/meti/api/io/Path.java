package com.meti.api.io;

import java.io.IOException;

public interface Path {
    void delete() throws IOException;

    void write(CharSequence output) throws IOException;

    Directory createDirectory() throws IOException;

    File createFile() throws IOException;

    boolean isExtinct();

    @Deprecated
    Path resolve(String name);

    java.nio.file.Path getRoot();

    Directory asDirectory();

    File asFile();

    boolean isExtant();
}
