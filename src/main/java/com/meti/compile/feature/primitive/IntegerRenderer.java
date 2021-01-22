package com.meti.compile.feature.primitive;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.stage.Renderer;
import com.meti.compile.token.*;

import java.util.Optional;

public class IntegerRenderer implements Renderer<Token> {
	public static final Renderer<Token> IntegerRenderer_ = new IntegerRenderer();

	private IntegerRenderer() {
	}

	@Override
	public Option<Token> render(Token token) {
		return render1(token)
				.map(Some::Some)
				.orElseGet(None::None);
	}

	private Optional<Token> render1(Token token) {
		if (Tokens.is(token, GroupAttribute.Integer)) {
			var value = token.apply(AbstractToken.Query.Value).asString();
			return Optional.of(new Content(value));
		}
		return Optional.empty();
	}
}
