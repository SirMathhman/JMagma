package com.meti.call.ifs;

import com.meti.Node;

import java.util.Objects;
import java.util.function.Function;

public class If_ implements Node {
    private static final String Format = "if(%s)%s";
    private final Node condition;
    private final Node value;

    public If_(Node condition, Node value) {
        this.condition = condition;
        this.value = value;
    }

    @Override
    public Node mapByChild(Function<Node, Node> mapping) {
        Node newCondition = mapping.apply(condition);
        Node newValue = mapping.apply(value);
        return new If_(newCondition, newValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        If_ if_ = (If_) o;
        return Objects.equals(condition, if_.condition) &&
               Objects.equals(value, if_.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, value);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.If;
    }

    @Override
    public String render() {
        String renderCondition = condition.render();
        String renderValue = value.render();
        return Format.formatted(renderCondition, renderValue);
    }
}
