package com.meti;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class CompoundTokenizer extends StringTokenizer<Node> {
    CompoundTokenizer(String content) {
        super(content);
    }

    protected abstract Stream<Function<String, Tokenizer<Node>>> streamFactories();

    @Override
    public Optional<Node> tokenize() {
        return streamFactories()
                .map(factory -> factory.apply(content))
                .map(Tokenizer::tokenize)
                .flatMap(Optional::stream)
                .findFirst();
    }
}
