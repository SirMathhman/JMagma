package com.meti.compile.feature.function;

import com.meti.compile.stage.Renderer;
import com.meti.compile.token.*;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.meti.compile.stage.CFieldRenderer.CFieldRenderer_;

public class FunctionRenderer implements Renderer<Token> {
	public static final Renderer<Token> FunctionRenderer_ = new FunctionRenderer();

	private FunctionRenderer() {
	}

	@Override
	public Optional<Token> render(Token token) {
		if (Tokens.is(token, GroupAttribute.Implementation)) {
			var identity = token.apply(Token.Query.Identity).asField();

			var name = identity.findName();
			var returns = identity.findType().apply(Token.Query.Returns).asToken();
			var parameters = token.apply(Token.Query.Parameters).asFieldList();
			var body = token.apply(Token.Query.Body).asToken();
			var renderedParams = parameters
					.stream()
					.map(CFieldRenderer_::render)
					.flatMap(Optional::stream)
					.collect(Collectors.toList());
			var joinedParameters = Parents.join(",", renderedParams);
			var formattedName = Parents.format(name + "(%t)")
					.format(joinedParameters)
					.complete();
			var identityPair = new Pair(returns, formattedName);
			return Optional.of(Parents.format("%t%t")
					.format(identityPair)
					.format(body)
					.complete());
		}
		return Optional.empty();
	}
}
