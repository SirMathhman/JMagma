package com.meti.compile.feature.primitive;

import com.meti.compile.stage.Renderer;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.Parents;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;

import java.util.Optional;

public class PrimitiveTypeRenderer implements Renderer<Token> {
	public static final Renderer<Token> PrimitiveTypeRenderer_ = new PrimitiveTypeRenderer();

	public PrimitiveTypeRenderer() {
	}

	@Override
	public Optional<Token> render(Token token) {
		if (Tokens.is(token, GroupAttribute.Pair)) {
			var name = token.apply(Token.Query.Name).asToken();
			var type = token.apply(Token.Query.Type).asToken();
			var typeString = type.apply(Token.Query.Value).asString();
			return Optional.of(Parents.format(typeString + " %t")
					.format(name)
					.complete());
		}
		return Optional.empty();
	}
}
