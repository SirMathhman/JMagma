package com.meti.compile.render.block.function;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.node.UnidentifiedException;

import java.util.function.Function;

public class Return implements Node {
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
    public Node withValue(Object value) {
        return new Return((Node) value);
    }

    @Override
    public String render() {
        return Format.formatted(value.render());
    }

    public Node mapByFields(Function<Field, Field> mapper) {
        return this;
    }

    @Override
    public Node mapByIdentity(Function<Field, Field> mapper) {
        return this;
    }

    @Override
    public Field identity() {
        var format = "No identity exists for: %s";
        var message = format.formatted(getClass());
        throw new UnidentifiedException(message);
    }

    @Override
    public Node withIdentity(Field identity) {
        var format = "No identity exists for: %s";
        var message = format.formatted(getClass());
        throw new UnidentifiedException(message);
    }
}
