package com.meti.compile.feature.evaluate;

import com.meti.compile.feature.node.NodeEvaluator;

public abstract class AbstractNodeEvaluator implements NodeEvaluator {
    protected final String content;

    public AbstractNodeEvaluator(String content) {
        this.content = content;
    }
}
