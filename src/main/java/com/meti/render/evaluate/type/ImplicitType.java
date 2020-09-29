package com.meti.render.evaluate.type;

public class ImplicitType implements EmptyType, LeafType, UnrenderableType {
    public static final Type ImplicitType = new ImplicitType();

    private ImplicitType() {
    }

    public static Type ImplicitType() {
        return ImplicitType;
    }
}
