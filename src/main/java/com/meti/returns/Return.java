package com.meti.returns;

import com.meti.Node;

import java.util.Objects;
import java.util.function.Function;

public class Return implements Node {
    private static final String Format = "return %s;";
    private final Node value;

    public Return(Node value) {
        this.value = value;
    }

    @Override
    public Node mapByChild(Function<Node, Node> mapping) {
        return new Return(mapping.apply(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Return aReturn = (Return) o;
        return Objects.equals(value, aReturn.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Return;
    }

    @Override
    public String render() {
        String render = value.render();
        return Format.formatted(render);
    }
}
