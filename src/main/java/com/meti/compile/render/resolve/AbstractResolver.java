package com.meti.compile.render.resolve;

import com.meti.compile.render.process.State;

public abstract class AbstractResolver implements Resolver {
    protected final State state;

    public AbstractResolver(State state) {
        this.state = state;
    }
}
