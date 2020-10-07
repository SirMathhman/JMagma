package com.meti.compile.render.primitive;

public enum Primitive implements EmptyType {
    U8("unsigned char"),
    U16("unsigned int"),
    U32("unsigned long"),
    U64("unsigned long long"),

    I8("char"),
    I16("int"),
    I32("long"),
    I64("long long"),

    Void("void"),
    Any("void"),
    Bool("int");

    private final String value;

    Primitive(String value) {
        this.value = value;
    }

    @Override
    public String render(String name) {
        return "%s %s".formatted(value, name);
    }
}
