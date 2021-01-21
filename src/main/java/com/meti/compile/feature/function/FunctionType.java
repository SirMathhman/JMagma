package com.meti.compile.feature.function;

import com.meti.compile.token.*;

import java.util.Collections;
import java.util.List;

public record FunctionType(Token returns, List<Token> parameters) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Function;
			case Returns -> new TokenAttribute(returns);
			case Parameters -> new TokenListAttribute(parameters);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return switch (query) {
			case Returns -> new FunctionType(attribute.asToken(), parameters);
			case Parameters -> new FunctionType(returns, attribute.asTokenList());
			default -> this;
		};
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return type == Attribute.Type.Type ?
				List.of(Query.Returns, Query.Parameters) :
				Collections.emptyList();
	}
}
