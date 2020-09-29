package com.meti.feature;

import java.util.Optional;

public interface Evaluator<T> {
    Optional<Evaluatable<T>> evaluate(String content);
}
