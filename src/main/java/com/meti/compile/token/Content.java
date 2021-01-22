package com.meti.compile.token;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Content extends AbstractToken {
	private final String value;

	public Content(String value) {
		this.value = value;
	}

	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Content;
			case Value -> new StringAttribute(value);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return type == Attribute.Type.Other ?
				List.of(Query.Group, Query.Value) :
				Collections.emptyList();
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Content) obj;
		return Objects.equals(this.value, that.value);
	}
}
