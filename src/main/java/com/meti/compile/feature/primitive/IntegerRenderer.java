package com.meti.compile.feature.primitive;

import com.meti.compile.stage.Renderer;
import com.meti.compile.token.*;

import java.util.Optional;

public class IntegerRenderer implements Renderer<Token> {
	public static final Renderer<Token> IntegerRenderer_ = new IntegerRenderer();

	private IntegerRenderer() {
	}

	@Override
	public Optional<Token> render(Token token) {
		if (Tokens.is(token, GroupAttribute.Integer)) {
			var value = token.apply(AbstractToken.Query.Value).asString();
			return Optional.of(new Content(value));
		}
		return Optional.empty();
	}
}
