package com.meti.compile.feature.function;

import com.meti.compile.token.Attribute;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.Token;
import com.meti.compile.token.TokenAttribute;

import java.util.Collections;
import java.util.List;

public record Return(Token value) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Return;
			case Value -> new TokenAttribute(value);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return query == Query.Value ? new Return(attribute.asToken()) : this;
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return type == Attribute.Type.Node ? Collections.singletonList(Query.Value) : Collections.emptyList();
	}
}
