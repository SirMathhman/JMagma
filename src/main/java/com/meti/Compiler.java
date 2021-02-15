package com.meti;

public class Compiler {
	public static final Compiler Compiler_ = new Compiler();

	private Compiler() {
	}

	String compile(String input) throws CompileException {
		if (input.isBlank()) throw new CompileException("Input is blank.");
		else if (isInteger(input)) {
			return input;
		} else return "int main(){return 0;}";
	}

	private boolean isInteger(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}