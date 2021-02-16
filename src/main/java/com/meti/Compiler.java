package com.meti;

public class Compiler {
	public static final Compiler Compiler_ = new Compiler();

	private Compiler() {
	}

	Renderable compile(Input input) throws CompileException {
		if (input.isEmpty()) return new EmptyOutput();
		var root = lex(input);
		return render(root);
	}

	private boolean isInteger(Input input) throws StreamException {
		return input.stream().allMatch(Character::isDigit);
	}

	private Token lex(Input input) throws CompileException {
		try {
			if (input.test()) {
				return new Return(lex(input.slice(7)));
			} else if (isInteger(input)) {
				return new Integer(input);
			} else return name -> new InputAttribute(new Input("int main(){return 0;}"));
		} catch (StreamException e) {
			throw new CompileException(e);
		}
	}

	private Output render(Token root) throws RenderException {
		try {
			if (root.apply(Attribute.Name.Group) == Token.Type.Return) {
				var root1 = root.apply(Attribute.Name.Value).computeToken();
				return ((Output) new StringOutput("return "))
						.concat(render(root1))
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