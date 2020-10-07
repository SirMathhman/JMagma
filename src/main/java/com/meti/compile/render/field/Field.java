package com.meti.compile.render.field;

import com.meti.compile.render.Renderable;

public interface Field extends Renderable {
    enum Flag {
        CONST,
        LET,
        NATIVE,
        IMPLICIT,
        DEF
    }
}
