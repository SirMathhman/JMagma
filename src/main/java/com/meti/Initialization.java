package com.meti;

import java.util.Objects;
import java.util.function.Function;

public class Initialization implements Node {
    private static final String Format = "%s=%s;";
    private final Field identity;
    private final Node value;


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

    Initialization(Field identity, Node value) {
        this.identity = identity;
        this.value = value;
    }

    @Override
    public Node mapByIdentity(Function<Field, Field> mapping) {
        return new Initialization(mapping.apply(identity), value);
    }

    @Override
    public Node mapByChild(Function<Node, Node> mapping) {
        return new Initialization(identity, mapping.apply(value));
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Declaration;
    }

    @Override
    public String render() {
        String renderedIdentity = identity.render();
        String renderedValue = value.render();
        return Format.formatted(renderedIdentity, renderedValue);
    }
}
