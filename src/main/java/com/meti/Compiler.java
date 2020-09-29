package com.meti;

import com.meti.feature.Content;
import com.meti.feature.Token;
import com.meti.feature.Tokenizable;
import com.meti.feature.Tokenizer;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class Compiler {
    protected abstract Stream<Tokenizer> tokenizers();

    String compileToString(String value) {
        return tokenize(new Content(value)).render();
    }

    private Token tokenize(Token token) {
        if (token.is(Token.Group.Content)) {
            Token value = token.transformContent(this::tokenize)
                    .orElseThrow(() -> createInvalidContent(token));
            return value.mapByChildren(this::tokenize);
        } else {
            return token;
        }
    }

    private CompileException createInvalidContent(Token token) {
        String format = "%s was of instance Content, but strangely didn't have any content to tokenize.";
        String message = String.format(format, token);
        return new CompileException(message);
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
