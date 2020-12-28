package com.meti.compile.process;

import java.util.stream.Stream;

import static com.meti.compile.feature.primitive.IntResolver.IntResolver_;

public class MagmaResolver extends CompoundResolver {
	public static final Resolver MagmaResolver_ = new MagmaResolver();

	public MagmaResolver() {
	}

	@Override
	protected Stream<Resolver> streamResolvers() {
		return Stream.of(IntResolver_);
	}
}
