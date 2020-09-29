package com.meti;

import com.meti.feature.*;

import java.util.stream.Stream;

public class MagmaCompiler extends Compiler {
    @Override
    protected Stream<Tokenizer> tokenizers() {
        return Stream.of(new IntTokenizer(),
                new FloatTokenizer(),
                new VariableTokenizer());
    }
}