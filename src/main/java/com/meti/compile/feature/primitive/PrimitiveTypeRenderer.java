package com.meti.compile.feature.primitive;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.stage.Renderer;
import com.meti.compile.token.*;

import java.util.Optional;

public class PrimitiveTypeRenderer implements Renderer<Token> {
	public static final Renderer<Token> PrimitiveTypeRenderer_ = new PrimitiveTypeRenderer();

	public PrimitiveTypeRenderer() {
	}

	@Override
	public Option<Token> render(Token token) {
		return render1(token)
				.map(Some::Some)
				.orElseGet(None::None);
	}

	private Optional<Token> render1(Token token) {
		if (Tokens.is(token, GroupAttribute.Pair)) {
			Token name = token.apply(AbstractToken.Query.Name).asToken();
			Token type = token.apply(AbstractToken.Query.Type).asToken();
			var typeString = type.apply(AbstractToken.Query.Value).asString();
			return Optional.of(Parents.format(typeString + " %t")
					.format(name)
					.complete());
		}
		return Optional.empty();
	}
}
