package com.meti;

public class Input {
	private final String input;

	public Input(String input) {
		this.input = input;
	}

	Output asOutput() {
		return new StringOutput(getInput());
	}

	boolean isEmpty() {
		return getInput().isBlank();
	}

	public String getInput() {
		return input;
	}

	Input slice(int extent) {
		var slice = getInput().substring(extent);
		var value = slice.trim();
		return new Input(value);
	}

	Stream<Character> stream() {
		return Strings.stream(getInput());
	}

	boolean test() {
		return getInput().startsWith("return ");
	}
}
