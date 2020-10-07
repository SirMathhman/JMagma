package com.meti.compile.feature.tokenize;

public abstract class AbstractTokenizer implements Tokenizer {
    protected final String content;

    public AbstractTokenizer(String content) {
        this.content = content;
    }
}
