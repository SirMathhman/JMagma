package com.meti.compile;

import com.meti.compile.state.State;

public abstract class AbstractParser implements Parser {
    protected final State previous;

    public AbstractParser(State previous) {
        this.previous = previous;
    }
}
