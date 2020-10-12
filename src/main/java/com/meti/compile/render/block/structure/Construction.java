package com.meti.compile.render.block.structure;

import com.meti.compile.render.node.Node;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Construction implements Node {
    private final List<Node> arguments;

    private Construction(List<Node> arguments) {
        this.arguments = arguments;
    }

    public static Node Construct(Node... arguments) {
        return Construct(List.of(arguments));
    }

    public static Node Construct(List<Node> arguments) {
        return new Construction(arguments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Construction that = (Construction) o;
        return arguments.containsAll(that.arguments) && that.arguments.containsAll(arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arguments);
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
