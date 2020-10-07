package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.resolve.MagmaResolver;
import com.meti.compile.render.resolve.Resolver;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TypeProcessor extends AbstractProcessor {
    private final List<Function<State, Processor>> Factories = List.of(
            state -> new InitializationProcessor(state, this::resolve)
    );

    public TypeProcessor(State state) {
        super(state);
    }

    private Resolver resolve(Node node) {
        return new MagmaResolver(node);
    }

    @Override
    public Optional<State> process() {
        return Factories.stream()
                .map(factory -> factory.apply(state))
                .map(Processor::process)
                .flatMap(Optional::stream)
                .findFirst();
    }
}
