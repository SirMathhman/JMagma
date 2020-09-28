package com.meti.feature;

public class Variable implements Token {
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public String render() {
        return value;
    }
}
