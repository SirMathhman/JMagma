package com.meti;

public record EqualityCompiler(String target, String output) implements Compiler {
	@Override
	public String compile(String input) {
		return input.equals(target) ? output : input;
	}
}
