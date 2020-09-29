package com.meti;

import com.meti.feature.*;

import java.util.stream.Stream;

public class MagmaCompiler extends Compiler {
    @Override
    protected Stream<TokenEvaluator> streamTokenEvaluators() {
        return Stream.of(
                new DeclareEvaluator(),
                new IntEvaluator(),
                new FloatEvaluator(),
                new VariableEvaluator());
    }

    @Override
    protected Stream<TypeEvaluator> streamTypeEvaluators() {
        return Stream.of(
                new PrimitiveEvaluator()
        );
    }
}