package com.meti.block;

import com.meti.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Block implements Node {
    public static final Node EmptyBlock = new Block(Collections.emptyList());
    private final List<Node> children;

    Block(List<Node> children) {
        this.children = children;
    }

    public static Builder Block() {
        return new Builder();
    }

    @Override
    public Node mapByChild(Function<Node, Node> mapping) {
        return children.stream()
                .map(mapping)
                .reduce(Block(), Builder::append, (builder, builder2) -> builder2)
                .complete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(children, block.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
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

    public static class Builder {
        private final List<Node> cache;

        Builder() {
            this(Collections.emptyList());
        }

        Builder(List<Node> cache) {
            this.cache = cache;
        }

        public Builder append(Node child) {
            List<Node> newCache = new ArrayList<>(cache);
            newCache.add(child);
            return new Builder(newCache);
        }

        public Node complete() {
            return new Block(cache);
        }
    }
}
