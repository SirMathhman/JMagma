package com.meti;

import com.meti.render.feature.primitive.FloatEvaluator;
import com.meti.render.feature.primitive.IntEvaluator;
import com.meti.render.feature.primitive.PrimitiveEvaluator;
import com.meti.render.feature.scope.DeclareEvaluatable;
import com.meti.render.feature.scope.InitializationEvaluatable;
import com.meti.render.feature.scope.VariableEvaluator;
import com.meti.render.evaluate.token.TokenEvaluator;
import com.meti.render.evaluate.type.TypeEvaluator;

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