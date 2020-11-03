package com.meti.compile.primitive.chars;

import com.meti.compile.Node;

class Char implements Node {
    private static final String Format = "'%s'";
    private final char value;

    Char(char value) {
        this.value = value;
    }

    @Override
    public String render() {
        return Format.formatted(value);
    }

    @Override
    public boolean is(Group group) {
       return group == Group.Char;
    }
}
