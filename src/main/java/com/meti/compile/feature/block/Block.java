package com.meti.compile.feature.block;

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
	public List<Query> list(Attribute.Type type) {
		return type == Attribute.Type.NodeList ?
				Collections.singletonList(Query.Lines) :
				super.list(null);
	}

	public List<Token> lines() {
		return lines;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Block) obj;
		return Objects.equals(this.lines, that.lines);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lines);
	}

	@Override
	public String toString() {
		return "Block[" +
		       "lines=" + lines + ']';
	}

}
