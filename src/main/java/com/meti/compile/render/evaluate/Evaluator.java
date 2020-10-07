package com.meti.compile.render.evaluate;

import com.meti.compile.render.Renderable;

import java.util.Optional;

public interface Evaluator<T extends Renderable> {
    Optional<T> evaluate();
}
