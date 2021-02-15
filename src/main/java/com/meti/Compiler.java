package com.meti;

public class Compiler {
	public static final Compiler Compiler_ = new Compiler();

	private Compiler() {
	}

	String compile(Input input1) throws CompileException {
		if (isEmpty(input1)) return "";
		else if (input1.test()) {
			var value = input1.slice(7);
			var actual = compile(new Input(value));
			return "return " + actual;
		} else if (isInteger(input1)) {
			return input1.getInput();
		} else return "int main(){return 0;}";
	}

	private boolean isEmpty(Input input1) {
		return input1.getInput().isBlank();
	}

	private boolean isInteger(Input input1) {
		var input = input1.getInput();
		return new Stream(input).allMatch(Character::isDigit);
	}
}