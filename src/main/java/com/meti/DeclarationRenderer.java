package com.meti;

import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;
import com.meti.output.CharOutput;
import com.meti.output.Output;

import java.util.Optional;

import static com.meti.output.ListOutput.ListOutput;

public class DeclarationRenderer implements Renderer<Token> {
	public static final Renderer<Token> DeclarationRenderer_ = new DeclarationRenderer();

	public DeclarationRenderer() {
	}

	@Override
	public Optional<Output> render(Token token) throws RenderException {
		try {
			if (Tokens.is(token, Token.Type.Declaration)) {
				var identity = token.apply(Attribute.Name.Identity).computeField();
				var field = ((Output) ListOutput()).append(new FieldOutput(identity)).append(new CharOutput(';'));
				return Optional.of(field);
			}
		} catch (AttributeException e) {
			throw new RenderException(e);
		}
		return Optional.empty();
	}
}
