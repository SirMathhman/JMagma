package com.meti.compile.render;

import com.meti.compile.output.Output;

import java.util.List;
import java.util.Optional;

public abstract class CompoundRenderer<T> implements Renderer<T> {
	@Override
	public Optional<Output> render(T t) throws RenderException {
		for (Renderer<T> renderer : streamRenderers()) {
			var output = renderer.render(t);
			if (output.isPresent()) {
				return output;
			}
		}
		return Optional.empty();
	}

	protected abstract List<Renderer<T>> streamRenderers();
}
