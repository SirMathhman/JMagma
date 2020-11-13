package com.meti.compile.scope.declare;

import com.meti.compile.scope.field.Field;
import com.meti.compile.Node;

import java.util.Objects;
import java.util.function.Function;

public class Declaration implements Node {
    private static final String Format = "%s;";
    private final Field identity;

    Declaration(Field identity) {
        this.identity = identity;
    }

    @Override
    public <T, R> R transformValue(Class<T> clazz, Function<T, R> function) {
        return function.apply(clazz.cast(identity));
    }

    @Override
    public Node mapByIdentity(Function<Field, Field> mapping) {
        return new Declaration(mapping.apply(identity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Declaration that = (Declaration) o;
        return Objects.equals(identity, that.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Declaration;
    }

    @Override
    public String render() {
        String render = identity.render();
        return Format.formatted(render);
    }
}
