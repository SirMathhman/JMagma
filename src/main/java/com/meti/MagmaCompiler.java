package com.meti;

import com.meti.feature.*;

import java.util.stream.Stream;

public class MagmaCompiler extends Compiler {
    @Override
    protected Stream<TokenEvaluator> streamTokenEvaluators() {
        return Stream.of(
                new InitializationEvaluatable(),
                new IntEvaluator(),
                new FloatEvaluator(),
                new DeclareEvaluatable(),
                new VariableEvaluator());
    }

    @Override
    protected Stream<TypeEvaluator> streamTypeEvaluators() {
        return Stream.of(
                new PrimitiveEvaluator()
        );
    }
}