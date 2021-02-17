package com.meti.compile.feature.variable;

import com.meti.compile.render.RenderException;
import com.meti.compile.render.Renderer;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.output.Output;

import java.util.Optional;

public class VariableRenderer implements Renderer<Token> {
	public static final Renderer<Token> VariableRenderer_ = new VariableRenderer();

	public VariableRenderer() {
	}

	@Override
	public Optional<Output> render(Token token) throws RenderException {
		try {
			if (Tokens.is(token, Token.Type.Variable)) {
				return Optional.of(token.apply(Attribute.Name.Value).computeInput().asOutput());
			}
		} catch (AttributeException e) {
			throw new RenderException(e);
		}
		return Optional.empty();
	}
}
