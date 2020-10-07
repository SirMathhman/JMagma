package com.meti.compile.feature.tokenize;

import java.util.Optional;

public interface Evaluator<T extends Renderable> {
    Optional<T> evaluate();
}
