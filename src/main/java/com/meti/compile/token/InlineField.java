package com.meti.compile.token;

public record InlineField(String value) implements Field {
	@Override
	public String render() {
		return value;
	}
}
