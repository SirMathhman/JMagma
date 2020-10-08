package com.meti.compile.render.process;

import com.meti.compile.render.function.FunctionParser;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.resolve.MagmaResolver;
import com.meti.compile.render.resolve.Resolver;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Parser extends CollectiveProcessor {
    private final List<Function<State, Processor>> Factories = List.of(
            InitializationProcessor::new,
            FunctionParser::new
    );

    public Parser(State state) {
        super(state);
    }

    private Resolver resolve(Node node) {
        return new MagmaResolver(node);
    }

    @Override
    protected Stream<Function<State, Processor>> streamFactories() {
        return Factories.stream();
    }
}
