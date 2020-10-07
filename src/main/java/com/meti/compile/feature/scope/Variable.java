package com.meti.compile.feature.scope;

import com.meti.compile.feature.node.Node;

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
