package com.meti.render.evaluate;

import java.util.Optional;

public interface Evaluator<T> {
    Optional<Evaluatable<T>> evaluate(String content);
}
