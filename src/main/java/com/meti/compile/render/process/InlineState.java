package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;

public class InlineState implements State {
    private final Node current;
    private final Stack scope;

    public InlineState(Node current, Stack scope) {
        this.current = current;
        this.scope = scope;
    }

    @Override
    public boolean has(Node.Group group) {
        return current.is(group);
    }

    @Override
    public Node value() {
        return current;
    }

    @Override
    public Stack scope() {
        return scope;
    }

    @Override
    public State with(Node node) {
        return new InlineState(node, scope);
    }
}
