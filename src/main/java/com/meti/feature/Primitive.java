package com.meti.feature;

public enum Primitive implements EmptyType, LeafType {
    Void("void"),
    Any("void"),
    Char("char"),
    Bool("int"),

    U8("unsigned char"),
    U16("unsigned int"),
    U32("unsigned long"),
    U64("unsigned long long"),
    I8("char"),
    I16("int"),
    I32("long"),
    I64("long long"),

    Float("float"),
    Double("double");

    private static final String Format = "%s %s";
    private final String value;

    Primitive(String value) {
        this.value = value;
    }

    @Override
    public String render(String name) {
        return String.format(Format, value, name);
    }
}
