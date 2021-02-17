package com.meti.compile.feature.integer;

import com.meti.compile.render.RenderException;
import com.meti.compile.render.Renderer;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.output.Output;
import com.meti.compile.token.output.StringOutput;

import java.util.Optional;

public class IntegerRenderer implements Renderer<Token> {
	public static final Renderer<Token> IntegerRenderer_ = new IntegerRenderer();

	public IntegerRenderer() {
	}

	@Override
	public Optional<Output> render(Token token) throws RenderException {
		try {
			if (Tokens.is(token, Token.Type.Integer)) {
				var value = token.apply(Attribute.Name.Value).computeInt();
				return Optional.of(new StringOutput(String.valueOf(value)));
			}
		} catch (AttributeException e) {
			throw new RenderException(e);
		}
		return Optional.empty();
	}
}
