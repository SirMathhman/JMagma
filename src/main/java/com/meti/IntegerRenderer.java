package com.meti;

import java.util.Optional;

public class IntegerRenderer implements Renderer<Token> {
	public static final Renderer<Token> IntegerRenderer_ = new IntegerRenderer();

	public IntegerRenderer() {
	}

	@Override
	public Optional<Output> render(Token token) throws RenderException {
		try {
			if (Tokens.is(token, Token.Type.Integer)) {
				var value = token.apply(Attribute.Name.Value).computeInt();
				return Optional.of(new StringOutput(String.valueOf(value)));
			}
		} catch (AttributeException e) {
			throw new RenderException(e);
		}
		return Optional.empty();
	}
}
