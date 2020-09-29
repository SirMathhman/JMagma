package com.meti.feature;

import java.util.function.Function;

public interface Field extends Renderable {
    Field mapByType(Function<Type, Type> mapping);

    boolean isFlagged(Flag flag);

    enum Flag {
        DEF,
        CONST,
        LET,
        NATIVE
    }
}
