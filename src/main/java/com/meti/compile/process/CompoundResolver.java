package com.meti.compile.process;

import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import java.util.stream.Stream;

import static com.meti.api.core.None.None;

public abstract class CompoundResolver implements Resolver {
	@Override
	public Option<Type> resolve(Node node) {
		return streamResolvers()
				.map(resolver -> resolver.resolve(node))
				.flatMap(option -> option.map(Stream::of).orElse(Stream.empty()))
				.findFirst()
				.map(Some::Some)
				.orElse(None());
	}

	protected abstract Stream<Resolver> streamResolvers();
}
