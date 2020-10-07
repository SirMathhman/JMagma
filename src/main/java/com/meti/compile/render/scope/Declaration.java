package com.meti.compile.render.scope;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;

public class Declaration implements Node {
    private final Field identity;

    public Declaration(Field identity) {
        this.identity = identity;
    }

    @Override
    public String render() {
        return identity.render() + ";";
    }
}
