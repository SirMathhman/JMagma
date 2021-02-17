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
	public Output append(Output output) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String compute() throws RenderException {
		throw new RenderException("Cannot render tokens.");
	}

	@Override
	public Output prepend(Output output) {
		return ListOutput()
				.append(output)
				.append(this);
	}

	@Override
	public Output replaceField(F1<Field, String> replacer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output replaceNode(F1<Token, String> replacer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output replaceType(F1<Token, String> replacer) {
		throw new UnsupportedOperationException();
	}

	public Output appendChar(char c) {
		throw new UnsupportedOperationException();
	}

	public Output appendField(Field field) {
		throw new UnsupportedOperationException();
	}

	public Output appendString(String s) {
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

	public Output prependChar(char c) {
		return ((Output) ListOutput()).append(new CharOutput(c))
				.append(this);
	}
}
