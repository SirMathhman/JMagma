package com.meti.compile.process;

import com.meti.api.core.Option;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public interface ConditionalResolver extends Resolver {
	@Override
	default Option<Type> resolve(Node node) throws ResolutionException {
		return canResolve(node) ? Some(resolveImpl(node)) : None();
	}

	boolean canResolve(Node node);

	Type resolveImpl(Node node) throws ResolutionException;
}
