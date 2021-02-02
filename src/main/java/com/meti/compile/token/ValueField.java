package com.meti.compile.token;

public record ValueField(Token type, String name, Token token) implements Field {
	@Override
	public Output render() {
		var renderedType = type.render().asString();
		var renderedToken = token.render().asString();
		return new Output("%s %s=%s".formatted(renderedType, name, renderedToken));
	}
}
