package com.meti.compile.render.scope;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;

import java.util.Objects;
import java.util.function.Function;

public class Initialization implements Node {
    private static final String Format = "%s=%s;";
    private final Field identity;
    private final Node value;

    private Initialization(Field identity, Node value) {
        this.identity = identity;
        this.value = value;
    }

    public static Node Initialize(Field identity, Node value) {
        return new Initialization(identity, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Initialization that = (Initialization) o;
        return Objects.equals(identity, that.identity) &&
               Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, value);
    }

    @Override
    public String render() {
        return Format.formatted(identity.render(), value.render());
    }

    @Override
    public Node mapByIdentity(Function<Field, Field> mapper) {
        return this.Initialize(mapper.apply(identity), value);
    }

    @Override
    public Node mapByChildren(Function<Node, Node> mapper) {
        return this.Initialize(identity, mapper.apply(value));
    }

    @Override
    public Node mapByFields(Function<Field, Field> mapper) {
        return this.Initialize(mapper.apply(identity), value);
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
        return this.Initialize(identity, value);
    }

    @Override
    public Node withValue(Object value) {
        return this.Initialize(identity, (Node) value);
    }
}
