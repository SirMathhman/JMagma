package com.meti.compile.render.block;

import com.meti.compile.render.node.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Block implements Node {
    public static final Node Block_ = Block();
    private final List<? extends Node> children;

    @Deprecated
    public Block(Node... children) {
        this(List.of(children));
    }

    private Block(List<? extends Node> children) {
        this.children = Collections.unmodifiableList(children);
    }

    public static Node Block(List<? extends Node> children) {
        return new Block(children);
    }

    public static Node Block(Node... children) {
        return new Block(children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return children.containsAll(block.children) &&
                block.children.containsAll(children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }

    @Override
    public String toString() {
        return "Block{" +
                "children=" + children +
                '}';
    }

    @Override
    public Stream<? extends Node> streamChildren() {
        return children.stream();
    }

    @Override
    public Node mapByChildren(Function<Node, Node> mapper) {
        return Block(mapChildren(mapper));
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
    public <T extends Container<T>> T reduce(T identity, Function<T, T> operator) {
        T current = identity;
        List<Node> newChildren = new ArrayList<>();
        for (Node child : children) {
            var withChild = current.with(child);
            current = operator.apply(withChild);
            newChildren.add(current.getValue());
        }
        return current.with(Block(newChildren));
    }

    @Override
    public String render() {
        return children.stream()
                .map(Node::render)
                .collect(Collectors.joining("", "{", "}"));
    }
}
