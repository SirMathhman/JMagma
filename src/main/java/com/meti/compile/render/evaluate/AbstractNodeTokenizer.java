package com.meti.compile.render.evaluate;

import com.meti.compile.render.node.NodeTokenizer;

public abstract class AbstractNodeTokenizer implements NodeTokenizer {
    protected final String content;

    public AbstractNodeTokenizer(String content) {
        this.content = content;
    }
}
