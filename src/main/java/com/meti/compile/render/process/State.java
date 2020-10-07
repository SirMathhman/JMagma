package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;

import java.util.function.Function;

public interface State {
    State attach(Node node, Function<Node, State> function);

    Node current();

    Stack scope();
}
