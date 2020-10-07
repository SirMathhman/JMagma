package com.meti.compile.render.node;

import java.util.function.Function;

public interface LeafNode extends Node {
    @Override
    default Node mapByChildren(Function<Node, Node> mapper) {
        return this;
    }
}
