package com.meti;

public abstract class StringTokenizer<T> implements Tokenizer<T> {
    protected final String content;

    public StringTokenizer(String content) {
        this.content = content;
    }
}
