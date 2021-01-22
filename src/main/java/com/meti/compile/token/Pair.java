package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequence;

import java.util.List;
import java.util.Objects;

public final class Pair extends AbstractToken {
	private final Token type;
	private final Token name;

	public Pair(Token type, Token name) {
		this.type = type;
		this.name = name;
	}

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
	public Sequence<Query> list(Attribute.Type type) {
		return JavaLists.fromJava(list1(type));
	}

	private List<Query> list1(Attribute.Type type) {
		return JavaLists.toJava(list(null));
	}

	public Token type() {
		return type;
	}

	public Token name() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Pair) obj;
		return Objects.equals(this.type, that.type) &&
		       Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, name);
	}

	@Override
	public String toString() {
		return "Pair[" +
		       "type=" + type + ", " +
		       "name=" + name + ']';
	}

}
