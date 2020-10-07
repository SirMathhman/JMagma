package com.meti.compile;

import com.meti.compile.feature.node.Node;
import com.meti.compile.feature.scope.VariableNodeEvaluator;
import com.meti.compile.feature.evaluate.Evaluator;
import com.meti.compile.feature.evaluate.IntNumberNodeEvaluator;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Compiler {
    private Stream<Function<String, Evaluator<Node>>> streamTokenizers() {
        return Stream.of(
                IntNumberNodeEvaluator::new,
                VariableNodeEvaluator::new
        );
    }

    public Node tokenize(String content) {
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
