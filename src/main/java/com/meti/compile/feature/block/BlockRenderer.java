package com.meti.compile.feature.block;

import com.meti.api.magma.core.Option;
import com.meti.compile.stage.Renderer;
import com.meti.compile.token.*;

import static com.meti.api.magma.core.Some.Some;

public class BlockRenderer implements Renderer<Token> {
	public static final Renderer<Token> BlockRenderer_ = new BlockRenderer();

	private BlockRenderer() {
	}

	@Override
	public Option<Token> render(Token token) {
		return Some(token)
				.filter(this::validate)
				.map(this::renderImpl);
	}

	private boolean validate(Token token) {
		return Tokens.is(token, GroupAttribute.Block);
	}

	private Token renderImpl(Token token) {
		var attribute = token.apply(AbstractToken.Query.Lines);
		var sequence = attribute.asTokenSequence();
		var parent = new Parent(sequence);
		return Parents.format("{%t}")
				.format(parent)
				.complete();
	}
}
