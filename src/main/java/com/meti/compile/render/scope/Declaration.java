package com.meti.compile.render.scope;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.EmptyNode;
import com.meti.compile.render.node.LeafNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.node.UnvaluedNode;

import java.util.function.Function;

public class Declaration implements EmptyNode, LeafNode, UnvaluedNode {
    private final Field identity;

    public Declaration(Field identity) {
        this.identity = identity;
    }

    @Override
    public String render() {
        return identity.render() + ";";
    }

    @Override
    public Node mapByIdentity(Function<Field, Field> mapper) {
        return new Declaration(mapper.apply(identity));
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
    public Node withIdentity(Field identity) {
        return new Declaration(identity);
    }
}
