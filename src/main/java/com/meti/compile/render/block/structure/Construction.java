package com.meti.compile.render.block.structure;

import com.meti.compile.render.node.Node;

import java.util.List;
import java.util.stream.Collectors;

public class Construction implements Node {
    private final List<Node> arguments;

    private Construction(List<Node> arguments) {
        this.arguments = arguments;
    }

    public static Node Construction(Node... arguments) {
        return Construction(List.of(arguments));
    }

    public static Node Construction(List<Node> arguments) {
        return new Construction(arguments);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Construction;
    }

    @Override
    public String render() {
        return arguments.stream()
                .map(Node::render)
                .collect(Collectors.joining(",", "{", "}"));
    }
}
