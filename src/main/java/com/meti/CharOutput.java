package com.meti;

import java.util.Objects;

public class CharOutput implements Output {
	private final char c;

	public CharOutput(char c) {
		this.c = c;
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
	public String compute() {
		return String.valueOf(c);
	}

	@Override
	public Output prependChar(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output prependString(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode() {
		return Objects.hash(c);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CharOutput that = (CharOutput) o;
		return c == that.c;
	}

	@Override
	public String toString() {
		return "{\"value\":\"%s\"}".formatted(c);
	}
}
