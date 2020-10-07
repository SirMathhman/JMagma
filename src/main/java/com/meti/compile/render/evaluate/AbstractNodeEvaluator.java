package com.meti.compile.render.evaluate;

import com.meti.compile.render.node.NodeEvaluator;

public abstract class AbstractNodeEvaluator implements NodeEvaluator {
    protected final String content;

    public AbstractNodeEvaluator(String content) {
        this.content = content;
    }
}
