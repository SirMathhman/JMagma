package com.meti.compile.feature.function;

import com.meti.compile.token.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Return extends AbstractToken {
	private final Token value;

	public Return(Token value) {
		this.value = value;
	}

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
		return type == Attribute.Type.Node ? Collections.singletonList(Query.Value) : super.list(null);
	}

	public Token value() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Return) obj;
		return Objects.equals(this.value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "Return[" +
		       "value=" + value + ']';
	}

}
