package com.meti.compile;

import com.meti.compile.render.process.Parser;
import com.meti.compile.render.process.State;

public class ParsingStage implements Stage<State, State> {
    public static final Stage<State, State> ParsingStage = new ParsingStage();

    public ParsingStage() {
    }

    @Override
    public State apply(State formatted) {
        return new Parser(formatted).process().orElse(formatted);
    }
}