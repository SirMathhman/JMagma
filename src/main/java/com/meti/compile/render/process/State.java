package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;

public interface State {

    boolean has(Node.Group group);

    Node current();

    Stack scope();

    State with(Node node);
}
