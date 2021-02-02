package com.meti.compile.token;

public class Output {
	private final String value;

	public Output(String value) {
		this.value = value;
	}

	public Output concat(Output next) {
		return new Output(value + next.getValue());
	}

	public String getValue() {
		return value;
	}
}
