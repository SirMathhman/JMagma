package com.meti.compile.feature.evaluate;

import com.meti.compile.feature.Renderable;

import java.util.Optional;

public interface Evaluator<T extends Renderable> {
    Optional<T> evaluate();
}
