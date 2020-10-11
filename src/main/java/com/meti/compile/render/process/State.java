package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;

import java.util.stream.Stream;

public interface State extends Node.Container<State> {
    Stream<Node> streamFunctions();

    Stream<Node> streamStructures();

    State appendFunctions(Node function);

    State appendStructure(Node structure);

    boolean has(Node.Group group);

    Stack getScope();

    State with(Stack scope);
}
