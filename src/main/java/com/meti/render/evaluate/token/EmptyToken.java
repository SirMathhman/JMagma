package com.meti.render.evaluate.token;

import java.util.Optional;
import java.util.function.Function;

public interface EmptyToken extends Token {
    default <R> Optional<R> transformContent(Function<String, R> mapping) {
        return Optional.empty();
    }
}
