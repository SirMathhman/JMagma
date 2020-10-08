package com.meti.compile.render.node;

import java.util.function.Function;

public interface EmptyNode extends Node {
    @Override
    default <T> T transformContent(Function<String, T> function) {
        throw new UntransformableException(getClass() + " has no content.");
    }
}
