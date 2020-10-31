package com.meti;

import java.util.Objects;
import java.util.function.Function;

public class While_ implements Node {
    private static final String Format = "while(%s)%s";
    private final Node condition;
    private final Node value;

    public While_(Node condition, Node value) {
        this.condition = condition;
        this.value = value;
    }

    @Override
    public Node mapByChild(Function<Node, Node> mapping) {
        Node newCondition = mapping.apply(condition);
        Node newValue = mapping.apply(value);
        return new While_(newCondition, newValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        While_ while_ = (While_) o;
        return Objects.equals(condition, while_.condition) &&
               Objects.equals(value, while_.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, value);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.While;
    }

    @Override
    public String render() {
        String renderCondition = condition.render();
        String renderValue = value.render();
        return Format.formatted(renderCondition, renderValue);
    }
}
