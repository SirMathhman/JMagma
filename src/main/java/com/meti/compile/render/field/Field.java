package com.meti.compile.render.field;

import com.meti.compile.render.Renderable;
import com.meti.compile.render.type.Type;

import java.util.function.Function;

public interface Field extends Renderable {
    Field mapByType(Function<Type, Type> function);

    enum Flag {
        CONST,
        LET,
        NATIVE,
        IMPLICIT,
        DEF
    }
}
