package com.meti;

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
