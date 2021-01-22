package com.meti.compile.feature.primitive;

import com.meti.api.java.collect.JavaList;
import com.meti.compile.token.*;

import java.util.List;
import java.util.Objects;

public final class Integer extends AbstractToken {
	private final String value;

	public Integer(String value) {
		this.value = value;
	}

	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Integer;
			case Value -> new StringAttribute(value);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Integer) obj;
		return Objects.equals(this.value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "Integer[" +
		       "value=" + value + ']';
	}

}
