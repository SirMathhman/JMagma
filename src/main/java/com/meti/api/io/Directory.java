package com.meti.api.io;

public interface Directory {
    File resolveFile(String name);

    Directory resolveDirectory(String name);

    Path resolve(String name);
}
