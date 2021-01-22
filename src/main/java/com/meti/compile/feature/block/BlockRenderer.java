package com.meti.compile.feature.block;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.stage.Renderer;
import com.meti.compile.token.*;

import java.util.Optional;

public class BlockRenderer implements Renderer<Token> {
	public static final Renderer<Token> BlockRenderer_ = new BlockRenderer();

	public BlockRenderer() {
	}

	@Override
	public Option<Token> render(Token token) {
		return render1(token)
				.map(Some::Some)
				.orElseGet(None::None);
	}

	private Optional<Token> render1(Token token) {
		if (Tokens.is(token, GroupAttribute.Block)) {
			var lines = JavaLists.toJava(token.apply(AbstractToken.Query.Lines).asTokenList());
			var lineAccumulator = new Parent(lines);
			var complete = Parents.format("{%t}")
					.format(lineAccumulator)
					.complete();
			return Optional.of(complete);
		}
		return Optional.empty();
	}
}
