package com.meti.compile.render.scope;

class Variable implements EmptyNode, UnfieldedNode {
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
