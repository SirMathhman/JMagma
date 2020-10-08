package com.meti.compile.render.scope;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;

import java.util.function.Function;

public class Initialization implements Node {
    private static final String Format = "%s=%s;";
    private final Field identity;
    private final Node value;

    public Initialization(Field identity, Node value) {
        this.identity = identity;
        this.value = value;
    }

    @Override
    public String render() {
        return Format.formatted(identity.render(), value.render());
    }

    @Override
    public Node mapByIdentity(Function<Field, Field> mapper) {
        return new Initialization(mapper.apply(identity), value);
    }

    @Override
    public Node mapByChildren(Function<Node, Node> mapper) {
        return new Initialization(identity, mapper.apply(value));
    }

    @Override
    public Node mapByFields(Function<Field, Field> mapper) {
        return new Initialization(mapper.apply(identity), value);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Initialization;
    }

    @Override
    public Field identity() {
        return identity;
    }

    @Override
    public <T> T value(Class<T> clazz) {
        return clazz.cast(value);
    }

    @Override
    public Node withIdentity(Field identity) {
        return new Initialization(identity, value);
    }

    @Override
    public Node withValue(Object value) {
        return new Initialization(identity, (Node) value);
    }
}
