package com.meti.compile.feature.block;

import com.meti.api.java.collect.JavaList;
import com.meti.api.java.collect.JavaLists;
import com.meti.compile.token.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Block extends AbstractToken {
	private final List<Token> lines;

	public Block(List<Token> lines) {
		this.lines = lines;
	}

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
		return query == Query.Lines ? new Block(JavaLists.toJava(attribute.asTokenList())) : this;
	}

	@Override
	public com.meti.api.magma.collect.List<Query> list(Attribute.Type type) {
		return new JavaList<>(type == Attribute.Type.NodeList ?
				Collections.singletonList(Query.Lines) :
				Collections.emptyList());
	}
}
