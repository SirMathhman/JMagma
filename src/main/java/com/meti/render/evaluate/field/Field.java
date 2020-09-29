package com.meti.render.evaluate.field;

import com.meti.render.Renderable;
import com.meti.render.evaluate.type.Type;

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
