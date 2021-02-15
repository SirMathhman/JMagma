package com.meti;

public class Output {
	public static final Output EmptyOutput = new Output("");
	private final String value;

	public Output(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
