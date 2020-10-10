package com.meti.compile.render.process;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class CollectiveProcessor extends AbstractProcessor {
    public CollectiveProcessor(State state) {
        super(state);
    }

    protected abstract CollectiveProcessor copy(State child);

    @Override
    public Optional<State> process() {
        var preprocessed = apply(streamPreprocessors(), state).orElse(state);
        var processed = apply(streamProcessors(), preprocessed).orElse(preprocessed);
        var withChildren = processed.value().reduce(processed, State::with, this::processChild);
        var postProcessed = apply(streamPostprocessors(), withChildren).orElse(withChildren);
        return Optional.of(postProcessed);
    }

    private State processChild(State state) {
        return copy(state)
                .process()
                .orElse(state);
    }

    private Optional<State> apply(Stream<Function<State, Processor>> stream, State state) {
        return stream.map(factory -> factory.apply(state))
                .map(Processor::process)
                .flatMap(Optional::stream)
                .findFirst();
    }

    protected Stream<Function<State, Processor>> streamPreprocessors() {
        return Stream.empty();
    }

    protected Stream<Function<State, Processor>> streamProcessors() {
        return Stream.empty();
    }

    protected Stream<Function<State, Processor>> streamPostprocessors() {
        return Stream.empty();
    }
}
