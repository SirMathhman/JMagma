package com.meti.compile.feature.tokenize;

public interface Tokenizer extends Evaluator<Tokenizer.Token> {
    interface Token extends Renderable {
    }
}
