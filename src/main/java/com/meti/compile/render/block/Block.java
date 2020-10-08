package com.meti.compile.render.block;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Block implements Node {
    private final List<? extends Node> children;

    public Block(Node... children) {
        this(List.of(children));
    }

    public Block(List<? extends Node> children) {
        this.children = Collections.unmodifiableList(children);
    }

    @Override
    public Stream<? extends Node> streamChildren() {
        return children.stream();
    }

    @Override
    public Node mapByChildren(Function<Node, Node> mapper) {
        return new Block(mapChildren(mapper));
    }

    private List<Node> mapChildren(Function<Node, Node> mapper) {
        return children.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Block;
    }

    @Override
    public String render() {
        return children.stream()
                .map(Node::render)
                .collect(Collectors.joining("", "{", "}"));
    }
}
