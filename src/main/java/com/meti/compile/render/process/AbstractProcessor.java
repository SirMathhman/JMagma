package com.meti.compile.render.process;

public abstract class AbstractProcessor implements Processor {
    protected final State state;

    public AbstractProcessor(State state) {
        this.state = state;
    }
}
