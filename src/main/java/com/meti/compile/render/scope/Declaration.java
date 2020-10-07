package com.meti.compile.render.scope;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;

import java.util.function.Function;

public class Declaration implements EmptyNode {
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
}
