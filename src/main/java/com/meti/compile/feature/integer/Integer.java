package com.meti.compile.feature.integer;

import com.meti.compile.token.Token;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.attribute.IntegerAttribute;

import java.util.Objects;

public class Integer implements Token {
	public static final Token Zero = new Integer(0);
	private final int value;

	public Integer(int value) {
		this.value = value;
	}

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Type -> Type.Integer;
			case Value -> new IntegerAttribute(value);
			default -> throw new AttributeException();
		};
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Integer integer = (Integer) o;
		return value == integer.value;
	}

	@Override
	public String toString() {
		return "{\"value\":%d}".formatted(value);
	}
}
