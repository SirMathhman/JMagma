package com.meti.compile.state;

import com.meti.compile.Node;

public class State {
    private final Node current;
    private final Stack stack;
    private final Cache cache;

    public State(Node current) {
        this(current, new Stack(), new Cache());
    }

    public State(Node current, Stack stack, Cache cache) {
        this.current = current;
        this.stack = stack;
        this.cache = cache;
    }
}
