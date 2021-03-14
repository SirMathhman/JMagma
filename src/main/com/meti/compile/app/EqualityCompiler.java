package com.meti.compile.app;

public record EqualityCompiler(String target, String output) implements Compiler {
	@Override
	public String compile(String input) {
		return input.equals(target) ? output : input;
	}

	static record WithTarget(String target) {
		static WithTarget EqualityCompiler(String target) {
			return new WithTarget(target);
		}

		Compiler complete(String output) {
			return new EqualityCompiler(target, output);
		}
	}
}
