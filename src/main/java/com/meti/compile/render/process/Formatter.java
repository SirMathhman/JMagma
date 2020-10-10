package com.meti.compile.render.process;

import com.meti.compile.render.function.FunctionFormatter;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.resolve.MagmaResolver;
import com.meti.compile.render.resolve.Resolver;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Formatter extends CollectiveProcessor {
    public Formatter(State state) {
        super(state);
    }

    @Override
    protected CollectiveProcessor copy(State child) {
        return new Formatter(child);
    }

    @Override
    protected Stream<Function<State, Processor>> streamProcessors() {
        return Stream.of(FunctionFormatter::new);
    }
}
