package com.meti.compile.token;

import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.attribute.InputAttribute;

import java.util.Objects;

public class Content implements Token {
	private final Input input;

	public Content(Input input) {
		this.input = input;
	}

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Type -> Token.Type.Content;
			case Value -> new InputAttribute(input);
			default -> throw new AttributeException();
		};
	}

	@Override
	public int hashCode() {
		return Objects.hash(input);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Content content = (Content) o;
		return Objects.equals(input, content.input);
	}

	@Override
	public String toString() {
		return "{\"input\":%s}".formatted(input);
	}
}
