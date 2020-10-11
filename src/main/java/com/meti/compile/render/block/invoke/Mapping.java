package com.meti.compile.render.block.invoke;

import com.meti.compile.render.node.Node;

import java.util.List;

public class Mapping extends Invocation {
    public Mapping(Node caller, List<? extends Node> arguments) {
        super(caller, arguments);
    }

    @Override
    protected Invocation complete(Node newCaller, List<? extends Node> newArguments) {
        return new Mapping(newCaller, newArguments);
    }

    @Override
    protected String extension() {
        return "";
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Mapping;
    }
}
