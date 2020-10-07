package com.meti.compile;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Compiler {
    private Stream<Function<String, Tokenizer>> streamTokenizers() {
        return Stream.of(IntNumberTokenizer::new);
    }

    public Tokenizer.Token tokenize(String content) {
        return streamTokenizers()
                .map(factory -> factory.apply(content))
                .map(Tokenizer::tokenize)
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Failed to tokenize: " + content));
    }

    public String compile(String content) {
        return tokenize(content).render();
    }
}
