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

	private boolean isInteger(Input input) {
		return input.stream().allMatch(Character::isDigit);
	}

	private Token lex(Input input) throws CompileException {
		if (input.test()) {
			return new Return(lex(input.slice(7)));
		} else if (isInteger(input)) {
			return new Integer(input);
		} else return name -> new InputAttribute(new Input("int main(){return 0;}"));
	}

	private Output render(Token root) throws RenderException {
		try {
			if (root.apply(Attribute.Name.Group) == Token.Group.Return) {
				var value = render(root.apply(Attribute.Name.Value).computeToken());
				return new Output("return ")
						.concat(value)
						.concat(";");
			}
		} catch (AttributeException e) {
			throw new RenderException("Failed to render return statement.", e);
		}

		try {
			return root.apply(Attribute.Name.Content).computeInput().asOutput();
		} catch (AttributeException e) {
			throw new RenderException("Unable to render token because no input was present.");
		}
	}
}