package com.meti.compile.token;

public record ValueField(Token type, String name, Token token) implements Field {
	@Override
	public String render() {
		var renderedType = type.render();
		var renderedToken = token.render();
		return "%s %s=%s".formatted(renderedType, name, renderedToken);
	}
}
