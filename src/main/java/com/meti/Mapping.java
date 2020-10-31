package com.meti;

import java.util.List;
import java.util.stream.Collectors;

public class Mapping implements Node{
    private final Node caller;
    private final List<Node> arguments;

    public Mapping(Node caller, List<Node> arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Mapping;
    }

    @Override
    public String render() {
        return caller.render() + renderArguments();
    }

    private String renderArguments() {
        return arguments.stream()
                .map(Node::render)
                .collect(Collectors.joining(",", "(", ")"));
    }
}
