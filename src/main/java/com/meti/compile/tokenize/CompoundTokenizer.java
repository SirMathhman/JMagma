package com.meti.compile.tokenize;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class CompoundTokenizer<T> extends AbstractTokenizer<T> {
    CompoundTokenizer(String content) {
        super(content);
    }

    protected abstract Stream<Function<String, Tokenizer<T>>> streamFactories();

    @Override
    public Optional<T> tokenize() {
        return streamFactories()
                .map(factory -> factory.apply(content))
                .map(Tokenizer::tokenize)
                .flatMap(Optional::stream)
                .findFirst();
    }
}
