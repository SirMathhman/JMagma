package com.meti.compile.feature.function;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.stage.Renderer;
import com.meti.compile.token.*;

import java.util.Optional;

public class ReturnRenderer implements Renderer<Token> {
	public static final Renderer<Token> ReturnRenderer_ = new ReturnRenderer();

	public ReturnRenderer() {
	}

	@Override
	public Option<Token> render(Token token) {
		return render1(token)
				.map(Some::Some)
				.orElseGet(None::None);
	}

	private Optional<Token> render1(Token token) {
		if (Tokens.is(token, GroupAttribute.Return)) {
			Token value = token.apply(AbstractToken.Query.Value).asToken();
			var node = Parents.format("return %t;")
					.format(value)
					.complete();
			return Optional.of(node);
		}
		return Optional.empty();
	}
}
