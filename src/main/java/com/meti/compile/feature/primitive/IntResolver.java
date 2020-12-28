package com.meti.compile.feature.primitive;

import com.meti.api.core.Option;
import com.meti.compile.process.Resolver;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class IntResolver implements Resolver {
	public static final IntResolver IntResolver_ = new IntResolver();

	private IntResolver() {
	}

	@Override
	public Option<Type> resolve(Node node) {
		if (node.is(Node.Group.Integer)) {
			return Some(Primitive.I16);
		}
		return None();
	}
}
