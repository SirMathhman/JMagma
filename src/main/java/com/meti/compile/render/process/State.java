package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;

public interface State extends Node.Container<State> {
    boolean has(Node.Group group);

    Stack scope();
}
