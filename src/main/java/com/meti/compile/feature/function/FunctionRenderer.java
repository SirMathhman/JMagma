package com.meti.compile.feature.function;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.ArrayLists;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.StreamException;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
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
	public Option<Token> render(Token token) {
		return render1(token)
				.map(Some::Some)
				.orElseGet(None::None);
	}

	private Optional<Token> render1(Token token) {
		if (Tokens.is(token, GroupAttribute.Implementation)) {
			var identity = token.apply(AbstractToken.Query.Identity).asField();

			var name = identity.findName();
			Token returns = identity.findType().apply(AbstractToken.Query.Returns).asToken();
			Sequence<Field> result;
			try {
				result = token.apply(AbstractToken.Query.Parameters).streamFields().fold(ArrayLists.empty(), List::add);
			} catch (StreamException e) {
				result = ArrayLists.empty();
			}
			var parameters = JavaLists.toJava(result);
			Token body = token.apply(AbstractToken.Query.Body).asToken();
			var renderedParams = parameters
					.stream()
					.map(token1 -> CFieldRenderer_.render(token1)
							.map(Optional::of)
							.orElseGet(Optional::empty))
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
