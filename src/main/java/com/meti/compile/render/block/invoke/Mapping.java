package com.meti.compile.render.block.invoke;

import com.meti.compile.render.node.Node;

import java.util.Collections;
import java.util.List;

public class Mapping extends Invocation {
    private Mapping(Node caller, List<? extends Node> arguments) {
        super(caller, arguments);
    }

    public static Mapping Mapping(Node caller) {
        return Mapping(caller, Collections.emptyList());
    }

    public static Mapping Mapping(Node caller, List<? extends Node> arguments) {
        return new Mapping(caller, arguments);
    }

    @Override
    protected Invocation complete(Node newCaller, List<? extends Node> newArguments) {
        return Mapping(newCaller, newArguments);
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
