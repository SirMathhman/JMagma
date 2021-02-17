package com.meti;

import java.util.Optional;

public interface Renderer<T> {
	Optional<Output> render(T t) throws RenderException;
}
