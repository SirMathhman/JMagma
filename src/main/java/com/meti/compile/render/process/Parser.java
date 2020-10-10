package com.meti.compile.render.process;

import com.meti.compile.render.function.FunctionParser;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Parser extends CollectiveProcessor {
    private final List<Function<State, Processor>> Factories = List.of(
            InitializationParser::new,
            FunctionParser::new
    );

    public Parser(State state) {
        super(state);
    }

    @Override
    protected CollectiveProcessor copy(State child) {
        return new Parser(child);
    }

    @Override
    protected Stream<Function<State, Processor>> streamProcessors() {
        return Factories.stream();
    }
}
