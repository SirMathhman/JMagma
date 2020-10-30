package com.meti;

import java.util.function.Function;
import java.util.stream.Stream;

public class NodeTokenizer extends CompoundTokenizer<Node> {
    public NodeTokenizer(String value) {
        super(value);
    }

    @Override
    protected Stream<Function<String, Tokenizer<Node>>> streamFactories() {
        return Stream.of(
                BlockTokenizer::new,
                ReturnTokenizer::new,
                InitializationTokenizer::new,
                DeclarationTokenizer::new,
                AssignmentTokenizer::new,
                CharTokenizer::new,
                IntTokenizer::new,
                VariableTokenizer::new);
    }
}
