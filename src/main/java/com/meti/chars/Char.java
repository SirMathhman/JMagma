package com.meti.chars;

import com.meti.Node;

class Char implements Node {
    public static final String Format = "'%s'";
    private final char value;

    public Char(char value) {
        this.value = value;
    }

    @Override
    public String render() {
        return Format.formatted(value);
    }

    @Override
    public boolean is(Group group) {
        throw new UnsupportedOperationException();
    }
}
