package com.meti.compile.render.scope;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.EmptyNode;
import com.meti.compile.render.node.LeafNode;
import com.meti.compile.render.node.Node;

import java.util.function.Function;

public class Declaration implements EmptyNode, LeafNode {
    private final Field identity;

    public Declaration(Field identity) {
        this.identity = identity;
    }

    @Override
    public String render() {
        return identity.render() + ";";
    }

    @Override
    public Node mapByFields(Function<Field, Field> mapper) {
        return new Declaration(mapper.apply(identity));
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Declaration;
    }

    @Override
    public Field identity() {
        return identity;
    }

    @Override
    public <T> T value(Class<T> clazz) {
        var format = "The declaration '%s' does not have a value.";
        var message = format.formatted(identity.name());
        throw new IllegalStateException(message);
    }

    @Override
    public Node withIdentity(Field identity) {
        return new Declaration(identity);
    }
}
