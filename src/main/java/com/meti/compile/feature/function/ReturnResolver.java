package com.meti.compile.feature.function;

import com.meti.compile.process.ConditionalResolver;
import com.meti.compile.process.ResolutionException;
import com.meti.compile.process.Resolver;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import static com.meti.compile.process.MagmaResolver.MagmaResolver_;

public class ReturnResolver implements ConditionalResolver {
	public static final Resolver ReturnResolver_ = new ReturnResolver();

	public ReturnResolver() {
	}

	@Override
	public boolean canResolve(Node node) {
		return node.is(Node.Group.Return);
	}

	@Override
	public Type resolveImpl(Node node) throws ResolutionException {
		var list = node.applyToChildrenExceptionally(MagmaResolver_::resolve);
		if (list.isEmpty()) {
			throw new ResolutionException("Return statement has no children.");
		} else {
			return list.get(0).orElseThrow(() -> new ResolutionException("Cannot resolve value of return statement."));
		}
	}
}
