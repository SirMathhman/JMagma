package com.meti;

import com.meti.feature.Token;
import com.meti.feature.Tokenizable;
import com.meti.feature.Tokenizer;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class Compiler {
    protected abstract Stream<Tokenizer> tokenizers();

    String compileToString(String value) {
        return tokenize(value).render();
    }

    private Token tokenize(String value) {
        return tokenizers()
                .map(tokenizer -> tokenizer.create(value))
                .flatMap(Optional::stream)
                .findFirst()
                .map(Tokenizable::tokenize)
                .orElseThrow(() -> createInvalidToken(value));
    }

    private CompileException createInvalidToken(String value) {
        String format = "Invalid token: %s";
        String message = String.format(format, value);
        return new CompileException(message);
    }
}
