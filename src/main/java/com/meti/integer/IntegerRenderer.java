package com.meti.integer;

import com.meti.RenderException;
import com.meti.Renderer;
import com.meti.Token;
import com.meti.Tokens;
import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;
import com.meti.output.Output;
import com.meti.output.StringOutput;

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
