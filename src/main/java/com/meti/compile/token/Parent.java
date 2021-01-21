package com.meti.compile.token;

import java.util.Collections;
import java.util.List;

public record Parent(List<Token> lines) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Parent;
			case Lines -> new TokenListAttribute(lines);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return query == Query.Lines ? new Parent(attribute.asTokenList()) : this;
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return type == Attribute.Type.NodeList ?
				Collections.singletonList(Query.Lines) :
				Collections.emptyList();
	}
}
