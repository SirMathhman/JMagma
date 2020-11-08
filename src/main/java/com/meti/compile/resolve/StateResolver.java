package com.meti.compile.resolve;

import com.meti.compile.Node;
import com.meti.compile.state.State;

public abstract class StateResolver implements Resolver<State> {
    protected final State state;

    public StateResolver(State state) {
        this.state = state;
    }

}
