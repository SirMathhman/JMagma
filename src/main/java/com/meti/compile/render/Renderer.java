package com.meti.compile.render;

import com.meti.compile.token.output.Output;

import java.util.Optional;

public interface Renderer<T> {
	Optional<Output> render(T t) throws RenderException;
}
