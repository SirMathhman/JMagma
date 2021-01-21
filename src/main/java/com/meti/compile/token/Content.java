package com.meti.compile.token;

public record Content(String value) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Content;
			case Value -> new StringAttribute(value);
			default -> throw new UnsupportedOperationException();
		};
	}
}
