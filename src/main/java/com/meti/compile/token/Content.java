package com.meti.compile.token;

public record Content(String value) implements Token {
	@Override
	public String render() {
		return value;
	}
}
