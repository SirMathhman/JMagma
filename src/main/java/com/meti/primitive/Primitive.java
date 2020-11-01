package com.meti.primitive;

import com.meti.Type;

public enum Primitive implements Type {
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

    public static final String Format = "%s %s";
    private final String value;

    Primitive(String value) {
        this.value = value;
    }

    @Override
    public String render(String name) {
        return Format.formatted(value, name);
    }

    @Override
    public boolean is(Group group){
        return false;
    }
}
