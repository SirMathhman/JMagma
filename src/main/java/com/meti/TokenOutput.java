package com.meti;

import java.util.Objects;

import static com.meti.ListOutput.ListOutput;

public abstract class TokenOutput implements Output {
	protected final Token token;

	public TokenOutput(Token token) {
		this.token = token;
	}

	@Override
	public Output append(Output output) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String compute() throws RenderException {
		throw new RenderException("Cannot render token of: " + token);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TokenOutput that = (TokenOutput) o;
		return Objects.equals(token, that.token);
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public Output prepend(Output output) {
		return ListOutput()
				.append(output)
				.append(this);
	}

	@Override
	public String toString() {
		return "{}";
	}
}
