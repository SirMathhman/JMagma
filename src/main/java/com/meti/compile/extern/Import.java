package com.meti.compile.extern;

import com.meti.compile.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Import implements Node {
    private final List<Node> children;

    public Import(List<Node> children) {
        this.children = children;
    }

    static Builder Import() {
        return new Builder(Collections.emptyList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Import anImport = (Import) o;
        return Objects.equals(children, anImport.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Import;
    }

    @Override
    public String render() {
        return children.stream()
                .map(Node::render)
                .collect(Collectors.joining());
    }

    static class Builder {
        private final List<Node> cache;

        Builder(List<Node> cache) {
            this.cache = cache;
        }

        public Builder with(Node node) {
            List<Node> copy = new ArrayList<>(cache);
            copy.add(node);
            return new Builder(copy);
        }

        public Node complete() {
            return new Import(cache);
        }
    }
}
