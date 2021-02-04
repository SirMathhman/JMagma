package com.meti.compile.token;

public record Content(String value) implements Token {
	@Override
	public Output render() {
		return new Output(value);
	}
}
