package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.List;
import java.util.Optional;

public abstract class CompoundRenderer<T> implements Renderer<T> {
	@Override
	public Optional<Token> render(T token) {
		return listRenderers()
				.stream()
				.map(renderer -> renderer.render(token))
				.flatMap(Optional::stream)
				.findFirst();
	}

	protected abstract List<Renderer<T>> listRenderers();
}
