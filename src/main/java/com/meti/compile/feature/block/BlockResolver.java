package com.meti.compile.feature.block;

import com.meti.api.core.Option;
import com.meti.compile.feature.primitive.Primitive;
import com.meti.compile.process.ConditionalResolver;
import com.meti.compile.process.ResolutionException;
import com.meti.compile.process.Resolver;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.meti.compile.process.MagmaResolver.MagmaResolver_;

public class BlockResolver implements ConditionalResolver {
	public static final Resolver BlockResolver_ = new BlockResolver();

	public BlockResolver() {
	}

	@Override
	public boolean canResolve(Node node) {
		return node.is(Node.Group.Block);
	}

	@Override
	public Type resolveImpl(Node node) throws ResolutionException {
		Function<Option<Type>, Stream<Type>> flattener = option -> option.map(Stream::of).orElseGet(Stream::empty);
		var types = node.applyToChildrenExceptionally(MagmaResolver_::resolve)
				.stream()
				.flatMap(flattener)
				.collect(Collectors.toList());
		if (types.isEmpty()) {
			return Primitive.Void;
		} else {
			return types.get(types.size() - 1);
		}
	}
}
