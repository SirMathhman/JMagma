package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InlineState implements State {
    private final Node value;
    private final Stack scope;
    private final List<Node> functions;
    private final List<Node> structures;

    public InlineState(Node value, Stack scope) {
        this(value, scope, new ArrayList<>(), new ArrayList<>());
    }

    public InlineState(Node value, Stack scope, List<Node> functions, List<Node> structures) {
        this.value = value;
        this.scope = scope;
        this.functions = functions;
        this.structures = structures;
    }

    @Override
    public Stream<Node> streamFunctions() {
        return functions.stream();
    }

    @Override
    public Stream<Node> streamStructures() {
        return structures.stream();
    }

    @Override
    public State appendFunctions(Node function) {
        List<Node> newFunctions = new ArrayList<>(functions);
        newFunctions.add(function);
        return new InlineState(value, scope, newFunctions, structures);
    }

    @Override
    public State appendStructure(Node structure) {
        List<Node> newStructures = new ArrayList<>(structures);
        newStructures.add(structure);
        return new InlineState(value, scope, functions, newStructures);
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
