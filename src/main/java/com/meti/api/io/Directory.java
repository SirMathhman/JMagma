package com.meti.api.io;

public interface Directory {
    Extant resolveFile(String name);

    Directory resolveDirectory(String name);

    Path resolve(String name);
}
