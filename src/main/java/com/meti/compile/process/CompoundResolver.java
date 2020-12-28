package com.meti.compile.process;

import com.meti.api.core.Option;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import java.util.List;

import static com.meti.api.core.None.None;

public abstract class CompoundResolver implements Resolver {
	@Override
	public Option<Type> resolve(Node node) throws ResolutionException {
		var resolvers = listResolvers();
		for (Resolver resolver : resolvers) {
			var option = resolver.resolve(node);
			if (option.isPresent()) {
				return option;
			}
		}
		return None();
	}

	protected abstract List<Resolver> listResolvers();
}
