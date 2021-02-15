package com.meti;

public class Compiler {
	public static final Compiler Compiler_ = new Compiler();

	private Compiler() {
	}

	Output compile(Input input) throws CompileException {
		if (input.isEmpty()) return Output.EmptyOutput;
		var root = lex(input);
		return render(root);
	}

	private Output render(Token root) {
		return root.apply(Attribute.Name.Output).asOutput();
	}

	private Token lex(Input input) throws CompileException {
		if (input.test()) {
			var node = compile(input.slice(7));
			return new OutputToken(new Output("return " + node.getValue()));
		} else if (isInteger(input)) {
			return new OutputToken(input.asOutput());
		} else return new OutputToken(new Output("int main(){return 0;}"));
	}

	private boolean isInteger(Input input) {
		return input.stream().allMatch(Character::isDigit);
	}

}