package com.meti;

public class Output {
	public static final Output EmptyOutput = new Output("");
	private final String value;

	public Output(String value) {
		this.value = value;
	}

	Output concat(String other) {
		return new Output(getValue() + other);
	}

	Output concat(Output other) {
		return new Output(getValue() + other.getValue());
	}

	public String getValue() {
		return value;
	}
}
