package com.meti.compile.render.evaluate;

import com.meti.compile.render.Renderable;

import java.util.Optional;

public interface Tokenizer<T extends Renderable> {
    Optional<T> evaluate();
}
