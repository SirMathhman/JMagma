package com.meti.compile.render.primitive;

import com.meti.compile.render.UntransformableException;
import com.meti.compile.render.type.Type;

import java.util.function.Function;

public interface EmptyType extends Type {
    @Override
    default <T> T transformContent(Function<String, T> transformer) {
        throw new UntransformableException("No content present in class " + getClass());
    }
}
