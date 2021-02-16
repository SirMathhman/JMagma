package com.meti;

public class MutableOutput implements Output {
	public static final Output EmptyOutput = new MutableOutput("");
	private final String value;

	public MutableOutput(String value) {
		this.value = value;
	}

	@Override
	public String compute() {
		return value;
	}

	@Override
	public Output concat(Output other) {
		return new MutableOutput(value + other.compute());
	}

	@Override
	public MutableOutput concat(String other) {
		return new MutableOutput(value + other);
	}
}
