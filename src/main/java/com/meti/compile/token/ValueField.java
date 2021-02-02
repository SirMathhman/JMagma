package com.meti.compile.token;

public record ValueField(Token type, String name, Token token) implements Field {
	@Override
	public String render() {
		var renderedType = type.render().getValue();
		var renderedToken = token.render().getValue();
		return "%s %s=%s".formatted(renderedType, name, renderedToken);
	}
}
