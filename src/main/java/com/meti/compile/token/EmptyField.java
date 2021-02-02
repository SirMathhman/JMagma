package com.meti.compile.token;

public record EmptyField(Token type, String name) implements Field {
	@Override
	public Output render() {
		return new Output("%s %s".formatted(type.render().asString(), name));
	}
}
