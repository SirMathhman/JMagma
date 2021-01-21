package com.meti.compile.token;

import java.util.Collections;
import java.util.List;

public record Pair(Token type, Token name) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Pair;
			case Type -> new TokenAttribute(type);
			case Name -> new TokenAttribute(name);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return this;
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return Collections.emptyList();
	}
}
