package com.meti.compile.render.node;

import java.util.List;
import java.util.function.Function;

public interface LeafNode extends Node {
    @Override
    default <T> T transformChildren(Function<List<? extends Node>, T> mapper) {
        var format = "There are no children to transform in instances of %s";
        var message = format.formatted(getClass());
        throw new UntransformableException(message);
    }

    @Override
    default Node mapByChildren(Function<Node, Node> mapper) {
        return this;
    }
}
