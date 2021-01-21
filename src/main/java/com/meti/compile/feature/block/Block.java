package com.meti.compile.feature.block;

import com.meti.compile.token.Attribute;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.Token;
import com.meti.compile.token.TokenListAttribute;

import java.util.Collections;
import java.util.List;

public record Block(List<Token> lines) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Block;
			case Lines -> new TokenListAttribute(lines);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return query == Query.Lines ? new Block(attribute.asTokenList()) : this;
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return type == Attribute.Type.NodeList ?
				Collections.singletonList(Query.Lines) :
				Collections.emptyList();
	}
}
