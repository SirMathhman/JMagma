package com.meti.compile.render.function;

import com.meti.compile.render.node.EmptyNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.node.UnfieldedNode;
import com.meti.compile.render.node.UnidentifiedNode;

import java.util.function.Function;

public class Return implements EmptyNode, UnfieldedNode, UnidentifiedNode {
    private final Node value;
    public static final String Format = "return %s;";

    public Return(Node value) {
        this.value = value;
    }

    @Override
    public Node mapByChildren(Function<Node, Node> mapper) {
        return new Return(mapper.apply(value));
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Return;
    }

    @Override
    public <T> T value(Class<T> clazz) {
        return clazz.cast(value);
    }

    @Override
    public String render() {
        return Format.formatted(value.render());
    }
}
