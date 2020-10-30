package com.meti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Block implements Node {
    private final List<Node> children;

    static Builder Block(){
        return new Builder();
    }

    static class Builder {
        private final List<Node> cache;

        Builder() {
            this(Collections.emptyList());
        }

        Builder(List<Node> cache) {
            this.cache = cache;
        }

        Builder append(Node child) {
            List<Node> newCache = new ArrayList<>(cache);
            newCache.add(child);
            return new Builder(newCache);
        }

        Node complete(){
            return new Block(cache);
        }
    }

    Block(List<Node> children) {
        this.children = children;
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
