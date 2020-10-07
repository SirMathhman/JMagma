package com.meti.compile;

import com.meti.compile.feature.scope.VariableTokenizer;
import com.meti.compile.feature.tokenize.Evaluator;
import com.meti.compile.feature.tokenize.IntNumberTokenizer;
import com.meti.compile.feature.tokenize.Tokenizer;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Compiler {
    private Stream<Function<String, Evaluator<Tokenizer.Token>>> streamTokenizers() {
        return Stream.of(
                IntNumberTokenizer::new,
                VariableTokenizer::new
        );
    }

    public Tokenizer.Token tokenize(String content) {
        return streamTokenizers()
                .map(factory -> factory.apply(content))
                .map(Evaluator::evaluate)
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Failed to evaluate: " + content));
    }

    public String compile(String content) {
        return tokenize(content).render();
    }
}
