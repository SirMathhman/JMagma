package com.meti;

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
}
