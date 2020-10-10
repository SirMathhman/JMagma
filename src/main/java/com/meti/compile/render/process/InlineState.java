package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;

public class InlineState implements State {
    private final Node value;
    private final Stack scope;

    public InlineState(Node value, Stack scope) {
        this.value = value;
        this.scope = scope;
    }

    @Override
    public boolean has(Node.Group group) {
        return value.is(group);
    }

    @Override
    public Node value() {
        return value;
    }

    @Override
    public Stack scope() {
        return scope;
    }

    @Override
    public State with(Stack scope) {
        return new InlineState(value, scope);
    }

    @Override
    public State with(Node node) {
        return new InlineState(node, scope);
    }
}
