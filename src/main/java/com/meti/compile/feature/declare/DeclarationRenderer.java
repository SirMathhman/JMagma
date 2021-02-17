package com.meti.compile.feature.declare;

import com.meti.compile.render.RenderException;
import com.meti.compile.render.Renderer;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.output.CharOutput;
import com.meti.compile.token.output.FieldOutput;
import com.meti.compile.token.output.Output;

import java.util.Optional;

import static com.meti.compile.token.output.ListOutput.ListOutput;

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
