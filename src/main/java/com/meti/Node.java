package com.meti;

import java.util.function.Function;

public interface Node extends Renderable {
    boolean is(Group group);

    default <T, R> R mapValue(Class<T> clazz, Function<T, R> function) {
        Class<? extends Node> thisClass = getClass();
        String format = "Instances of '%s' have no value.";
        String formatted = format.formatted(thisClass);
        throw new IllegalStateException(formatted);
    }

    default Node mapByChild(Function<Node, Node> function) {
        return this;
    }

    enum Group {
        Char,
        Int,
        Content, Declaration,
    }
}
