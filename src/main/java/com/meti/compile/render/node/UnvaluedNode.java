package com.meti.compile.render.node;

public interface UnvaluedNode extends Node {
    @Override
    default <T> T value(Class<T> clazz) {
        var format = "Node %s does not have a value.";
        var message = format.formatted(getClass());
        throw new UnvaluedException(message);
    }
}
