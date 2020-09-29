package com.meti;

import com.meti.feature.*;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class Compiler {
    protected abstract Stream<TokenEvaluator> streamTokenEvaluators();

    protected abstract Stream<TypeEvaluator> streamTypeEvaluators();

    String compileToString(String value) {
        return tokenize(new Content(value)).render();
    }

    private Token tokenize(Token token) {
        if (token.is(Token.Group.Content)) {
            Token value = token.transformContent(this::evaluateToken)
                    .orElseThrow(() -> createInvalidContent(token));
            return value.mapByChildren(this::tokenize)
                    .mapByFields(this::tokenize)
                    .mapByTypes(this::tokenize);
        } else {
            return token;
        }
    }

    private Type tokenize(Type type) {
        return type.transformContent(this::evaluateType)
                .orElseThrow(() -> createInvalidType(type))
                .mapByFields(this::tokenize)
                .mapByChildren(this::tokenize);
    }

    private Field tokenize(Field field) {
        return field.mapByType(this::tokenize);
    }

    private CompileException createInvalidContent(Token token) {
        String format = "%s was of instance Content, but strangely didn't have any content to complete.";
        String message = String.format(format, token);
        return new CompileException(message);
    }

    private Type evaluateType(String value) {
        return streamTypeEvaluators()
                .map(evaluator -> evaluator.evaluate(value))
                .flatMap(Optional::stream)
                .findFirst()
                .map(Evaluatable::complete)
                .orElseThrow(() -> createInvalidItem(value));
    }

    private Token evaluateToken(String value) {
        return streamTokenEvaluators()
                .map(evaluator -> evaluator.evaluate(value))
                .flatMap(Optional::stream)
                .findFirst()
                .map(Evaluatable::complete)
                .orElseThrow(() -> createInvalidItem(value));
    }

    private CompileException createInvalidItem(String value) {
        String format = "Invalid item: %s";
        String message = String.format(format, value);
        return new CompileException(message);
    }

    private CompileException createInvalidType(Type value) {
        String format = "Invalid type: %s";
        return value.transformContent((content) -> String.format(format, content))
                .map(CompileException::new)
                .orElseThrow();
    }
}
