package com.meti.compile.render.resolve;

import com.meti.compile.render.node.Node;

public abstract class AbstractResolver implements Resolver {
    protected final Node current;

    public AbstractResolver(Node current) {
        this.current = current;
    }
}
