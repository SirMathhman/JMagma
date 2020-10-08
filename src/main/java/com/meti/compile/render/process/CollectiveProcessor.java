package com.meti.compile.render.process;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class CollectiveProcessor extends AbstractProcessor {
    public CollectiveProcessor(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        return streamFactories()
                .map(factory -> factory.apply(state))
                .map(Processor::process)
                .flatMap(Optional::stream)
                .findFirst();
    }

    protected abstract Stream<Function<State, Processor>> streamFactories();
}
