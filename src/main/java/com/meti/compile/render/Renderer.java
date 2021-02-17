package com.meti.compile.render;

import com.meti.compile.output.Output;

import java.util.Optional;

public interface Renderer<T> {
	Optional<Output> render(T t) throws RenderException;
}
