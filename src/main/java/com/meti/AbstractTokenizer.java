package com.meti;

public abstract class AbstractTokenizer<T> implements Tokenizer<T> {
    protected final String content;

    public AbstractTokenizer(String content) {
        this.content = content;
    }
}
