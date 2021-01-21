package com.meti.compile.feature.block;

import com.meti.compile.stage.Renderer;
import com.meti.compile.token.*;

import java.util.Optional;

public class BlockRenderer implements Renderer<Token> {
	public static final Renderer<Token> BlockRenderer_ = new BlockRenderer();

	public BlockRenderer() {
	}

	@Override
	public Optional<Token> render(Token token) {
		if (Tokens.is(token, GroupAttribute.Block)) {
			var lines = token.apply(AbstractToken.Query.Lines).asTokenList();
			var lineAccumulator = new Parent(lines);
			var complete = Parents.format("{%t}")
					.format(lineAccumulator)
					.complete();
			return Optional.of(complete);
		}
		return Optional.empty();
	}
}
