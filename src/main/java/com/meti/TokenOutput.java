package com.meti;

import java.util.Objects;

import static com.meti.ListOutput.ListOutput;

public class TokenOutput implements Output {
	private final Token token;

	TokenOutput(Token token) {
		this.token = token;
	}

	public static Output TokenOutput(Token token) {
		return new TokenOutput(token);
	}

	@Override
	public Output appendChar(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output appendOutput(Output output) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output appendString(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String compute() throws RenderException {
		throw new RenderException("Cannot render tokens.");
	}

	@Override
	public Output prependChar(char c) {
		return ListOutput()
				.appendChar(c)
				.appendOutput(this);
	}

	@Override
	public Output prependString(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TokenOutput that = (TokenOutput) o;
		return Objects.equals(token, that.token);
	}

	@Override
	public String toString() {
		return "{}";
	}
}
