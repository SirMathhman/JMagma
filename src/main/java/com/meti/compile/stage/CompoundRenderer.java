package com.meti.compile.stage;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Token;

import java.util.List;
import java.util.Optional;

public abstract class CompoundRenderer<T> implements Renderer<T> {
	@Override
	public Option<Token> render(T token) {
		return render1(token)
				.map(Some::Some)
				.orElseGet(None::None);
	}

	private Optional<Token> render1(T token) {
		return listRenderers()
				.stream()
				.map(renderer -> renderer.render(token)
								.map(Optional::of)
								.orElseGet(Optional::empty))
				.flatMap(Optional::stream)
				.findFirst();
	}

	protected abstract List<Renderer<T>> listRenderers();
}
