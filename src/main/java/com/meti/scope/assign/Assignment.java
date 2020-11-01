package com.meti.scope.assign;

import com.meti.Node;

import java.util.Objects;
import java.util.function.Function;

public class Assignment implements Node {
    private static final String Format = "%s=%s;";
    private final Node from;
    private final Node to;

    public Assignment(Node to, Node from) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(from, that.from) &&
               Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public Node mapByChild(Function<Node, Node> mapping) {
        Node newFrom = mapping.apply(from);
        Node newTo = mapping.apply(to);
        return new Assignment(newTo, newFrom);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Assignment;
    }

    @Override
    public String render() {
        String renderedTo = to.render();
        String fromTo = from.render();
        return Format.formatted(renderedTo, fromTo);
    }
}
