package com.meti;

public interface Source {
    String read() throws SourceException;

    void write(String value) throws SourceException;
}
