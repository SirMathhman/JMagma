package com.meti.compile.feature.scope;

import com.meti.compile.feature.tokenize.Tokenizer;

class Variable implements Tokenizer.Token {
    private final String content;

    public Variable(String content) {
        this.content = content;
    }

    @Override
    public String render() {
        return content;
    }
}
