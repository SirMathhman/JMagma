package com.meti.compile.feature.function;

import com.meti.compile.stage.Renderer;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.Parents;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;

import java.util.Optional;

public class ReturnRenderer implements Renderer<Token> {
	public static final Renderer<Token> ReturnRenderer_ = new ReturnRenderer();

	public ReturnRenderer() {
	}

	@Override
	public Optional<Token> render(Token token) {
		if (Tokens.is(token, GroupAttribute.Return)) {
			var value = token.apply(Token.Query.Value).asToken();
			var node = Parents.format("return %t;")
					.format(value)
					.complete();
			return Optional.of(node);
		}
		return Optional.empty();
	}
}
