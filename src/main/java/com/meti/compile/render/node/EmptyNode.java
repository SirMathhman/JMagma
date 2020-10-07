package com.meti.compile.render.node;

import com.meti.compile.render.UntransformableException;
import com.meti.compile.render.node.Node;

import java.util.function.Function;

public interface EmptyNode extends Node {
    @Override
    default <T> T transformContent(Function<String, T> function) {
        throw new UntransformableException(getClass() + " has no content.");
    }
}
