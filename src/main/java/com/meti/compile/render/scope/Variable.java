package com.meti.compile.render.scope;

import com.meti.compile.render.node.*;

class Variable implements LeafNode, Node {
    private final String content;

    public Variable(String content) {
        this.content = content;
    }

    @Override
    public String render() {
        return content;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Variable;
    }

    @Override
    public <T> T value(Class<T> clazz) {
        return clazz.cast(content);
    }

    @Override
    public Node withValue(Object value) {
        return new Variable(value.toString());
    }
}
