package com.meti.compile.render.scope;

import com.meti.compile.render.node.EmptyNode;
import com.meti.compile.render.node.LeafNode;

class Variable implements EmptyNode, LeafNode, UnfieldedNode {
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
}
