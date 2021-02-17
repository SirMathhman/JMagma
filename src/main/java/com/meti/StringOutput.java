package com.meti;

import java.util.Objects;

public class StringOutput implements Output {
	private final String value;

	public StringOutput(String value) {
		this.value = value;
	}

	@Override
	public Output appendChar(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output appendField(Field field) {
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
		return value;
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
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StringOutput that = (StringOutput) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public String toString() {
		return "{\"value\":\"%s\"}".formatted(value);
	}
}
