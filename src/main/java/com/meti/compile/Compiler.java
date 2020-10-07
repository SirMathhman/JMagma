package com.meti.compile;

import com.meti.compile.render.evaluate.Evaluator;
import com.meti.compile.render.evaluate.IntEvaluator;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.PrimitiveEvaluator;
import com.meti.compile.render.scope.DeclarationEvaluator;
import com.meti.compile.render.scope.VariableEvaluator;
import com.meti.compile.render.type.Type;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Compiler {
    private Stream<Function<String, Evaluator<Node>>> streamTokenizers() {
        return Stream.of(
                DeclarationEvaluator::new,
                IntEvaluator::new,
                VariableEvaluator::new
        );
    }

    private Stream<Function<String, Evaluator<Type>>> streamTypeEvaluators() {
        return Stream.of(
                PrimitiveEvaluator::new
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
