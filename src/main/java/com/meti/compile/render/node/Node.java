package com.meti.compile.render.node;

import com.meti.compile.render.Renderable;

import java.util.function.Function;

public interface Node extends Renderable {
    <T> T transformContent(Function<String, T> function);
}
