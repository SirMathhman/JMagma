package com.meti;

public abstract class StringTokenizer implements Tokenizer {
    protected final String content;

    public StringTokenizer(String content) {
        this.content = content;
    }
}
