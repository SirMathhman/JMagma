package com.meti.compile.render.field;

import com.meti.compile.render.Renderable;
import com.meti.compile.render.type.Type;

import java.util.function.Function;

public interface Field extends Renderable {
    Field mapType(Function<Type, Type> function);

    String name();

    Type type();

    enum Flag {
        CONST,
        LET,
        NATIVE,
        IMPLICIT,
        DEF
    }
}
