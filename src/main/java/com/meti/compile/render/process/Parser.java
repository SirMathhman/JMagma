package com.meti.compile.render.process;

import com.meti.compile.render.function.FunctionParser;
import com.meti.compile.render.scope.VariableParser;

import java.util.function.Function;
import java.util.stream.Stream;

public class Parser extends CollectiveProcessor {

    public Parser(State state) {
        super(state);
    }

    @Override
    protected CollectiveProcessor copy(State child) {
        return new Parser(child);
    }

    @Override
    protected Stream<Function<State, Processor>> streamProcessors() {
        return Stream.of(
                InitializationParser::new,
                FunctionParser::new,
                VariableParser::new
        );
    }
}
