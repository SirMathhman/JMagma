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

    public <T> T transformBoth(BiFunction<Node, Stack, T> mapping) {
        return mapping.apply(current, stack);
    }

    public <T> T transformCurrent(Function<Node, T> function) {
        return function.apply(current);
    }

    public boolean has(Node.Group group) {
        return current.is(group);
    }

    public State mapByStack(BiFunction<Node, Stack, Stack> mapper) {
        Stack newStack = mapper.apply(current, stack);
        return new State(current, newStack, cache);
    }

    public State mapByCurrent(Function<Node, Node> mapper) {
        Node newCurrent = mapper.apply(current);
        return new State(newCurrent, stack, cache);
    }

    public <T> T transformStack(Function<Stack, T> mapper) {
        return mapper.apply(stack);
    }
}
