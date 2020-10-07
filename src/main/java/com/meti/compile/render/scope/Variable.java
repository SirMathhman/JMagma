package com.meti.compile.render.scope;

import com.meti.compile.render.node.Node;

class Variable implements Node {
    private final String content;

    public Variable(String content) {
        this.content = content;
    }

    @Override
    public String render() {
        return content;
    }
}
