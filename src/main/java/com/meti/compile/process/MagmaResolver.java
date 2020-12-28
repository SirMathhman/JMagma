package com.meti.compile.process;

import com.meti.compile.feature.function.ReturnResolver;

import java.util.List;

import static com.meti.compile.feature.block.BlockResolver.BlockResolver_;
import static com.meti.compile.feature.function.ReturnResolver.ReturnResolver_;
import static com.meti.compile.feature.primitive.IntResolver.IntResolver_;

public class MagmaResolver extends CompoundResolver {
	public static final Resolver MagmaResolver_ = new MagmaResolver();

	public MagmaResolver() {
	}

	@Override
	protected List<Resolver> listResolvers() {
		return List.of(
				BlockResolver_,
				IntResolver_,
				ReturnResolver_);
	}
}
