package com.meti.render.evaluate.type;

import java.util.Optional;
import java.util.function.Function;

public interface EmptyType extends Type {
    @Override
    default <R> Optional<R> transformContent(Function<String, R> mapping) {
        return Optional.empty();
    }
}
