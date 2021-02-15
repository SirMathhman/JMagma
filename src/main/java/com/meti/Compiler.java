package com.meti;

public class Compiler {
	public static final Compiler Compiler_ = new Compiler();

	private Compiler() {
	}

	String compile(String input) throws CompileException {
		if (input.isBlank()) throw new CompileException("Input is blank.");
		return "int main(){return 0;}";
	}
}