package com.meti.compile.token;

public record EmptyField(Token type, String name) implements Field {
	@Override
	public String render() {
		return "%s %s".formatted(type.render(), name);
	}
}
