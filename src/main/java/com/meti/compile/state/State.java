package com.meti.compile.state;

import com.meti.compile.Node;

import java.util.function.BiFunction;
import java.util.function.Function;

public class State {
    private final Node current;
    private final Stack stack;
    private final Cache cache;

    public State(Node current) {
        this(current, new Stack(), new Cache());
    }

    public State(Node current, Stack stack) {
        this(current, stack, new Cache());
    }

    public State(Node current, Stack stack, Cache cache) {
        this.current = current;
        this.stack = stack;
        this.cache = cache;
    }

    public <T> T map(BiFunction<Node, Stack, T> mapping) {
        return mapping.apply(current, stack);
    }

    public <T> T mapCurrent(Function<Node, T> function) {
        return function.apply(current);
    }

    public boolean has(Node.Group group) {
        return current.is(group);
    }
}
