package com.meti;

public class Input {
	private final String input;

	public Input(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	String slice(int from) {
		var slice = getInput().substring(from, getInput().length());
		var value = slice.trim();
		return value;
	}

	boolean test() {
		return getInput().startsWith("return ");
	}
}
