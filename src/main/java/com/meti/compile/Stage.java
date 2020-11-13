package com.meti.compile;

public interface Stage<T, R> {
    R apply(T content);
}
