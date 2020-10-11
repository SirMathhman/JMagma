package com.meti.compile.render.block.invoke;

import com.meti.compile.render.node.Node;

import java.util.List;

public class Procedure extends Invocation {
    public Procedure(Node caller, List<? extends Node> arguments) {
        super(caller, arguments);
    }

    @Override
    protected Invocation complete(Node newCaller, List<Node> newArguments) {
        return new Procedure(newCaller, newArguments);
    }

    @Override
    protected String extension() {
        return ";";
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Procedure;
    }
}
