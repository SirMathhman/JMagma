package com.meti.compile.feature.scope;

import com.meti.compile.feature.field.Field;
import com.meti.compile.feature.node.Node;

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
