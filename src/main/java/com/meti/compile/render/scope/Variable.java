package com.meti.compile.render.scope;

import com.meti.compile.render.node.EmptyNode;
import com.meti.compile.render.node.LeafNode;
import com.meti.compile.render.node.UnfieldedNode;
import com.meti.compile.render.node.UnidentifiedNode;

class Variable implements EmptyNode, LeafNode, UnidentifiedNode, UnfieldedNode {
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

}
