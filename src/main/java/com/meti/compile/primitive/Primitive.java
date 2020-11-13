package com.meti.compile.primitive;

import com.meti.compile.Node;
import com.meti.compile.Type;
import com.meti.compile.primitive.ints.Int;

import java.math.BigInteger;

public enum Primitive implements Type {
    U8("unsigned char", new Int(BigInteger.ZERO)),
    U16("unsigned int", new Int(BigInteger.ZERO)),
    U32("unsigned long", new Int(BigInteger.ZERO)),
    U64("unsigned long long", new Int(BigInteger.ZERO)),
    I8("char", new Int(BigInteger.ZERO)),
    I16("int", new Int(BigInteger.ZERO)),
    I32("long", new Int(BigInteger.ZERO)),
    I64("long long", new Int(BigInteger.ZERO)),
    Any("void*", new Int(BigInteger.ZERO)),
    Bool("int", new Int(BigInteger.ZERO));

    public static final String Format = "%s %s";
    private final String value;
    private final Node defaultValue;

    Primitive(String value, Node defaultValue) {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    @Override
    public String render(String name) {
        return Format.formatted(value, name);
    }

    @Override
    public Node createDefault() {
        return defaultValue;
    }

    @Override
    public boolean is(Group group) {
        return false;
    }
}
