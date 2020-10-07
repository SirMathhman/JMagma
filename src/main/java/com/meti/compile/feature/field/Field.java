package com.meti.compile.feature.field;

import com.meti.compile.feature.Renderable;

public interface Field extends Renderable {
    enum Flag {
        CONST,
        LET,
        NATIVE,
        IMPLICIT,
        DEF
    }
}
