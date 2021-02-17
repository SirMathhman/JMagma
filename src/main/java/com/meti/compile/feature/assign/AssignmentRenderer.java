package com.meti.compile.feature.assign;

import com.meti.compile.render.RenderException;
import com.meti.compile.render.Renderer;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.output.CharOutput;
import com.meti.compile.token.output.Output;
import com.meti.compile.token.output.TokenOutput;

import java.util.Optional;

import static com.meti.compile.token.output.ListOutput.ListOutput;

public class AssignmentRenderer implements Renderer<Token> {
	public static final Renderer<Token> AssignmentRenderer_ = new AssignmentRenderer();

	public AssignmentRenderer() {
	}

	@Override
	public Optional<Output> render(Token token) throws RenderException {
		try {
			if (Tokens.is(token, Token.Type.Assignment)) {
				var destination = token.apply(Attribute.Name.Destination).computeToken();
				var source = token.apply(Attribute.Name.Source).computeToken();
				return Optional.of(ListOutput()
						.append(new TokenOutput(destination))
						.append(new CharOutput('='))
						.append(new TokenOutput(source))
						.append(new CharOutput(';')));
			}
		} catch (AttributeException e) {
			throw new RenderException(e);
		}
		return Optional.empty();
	}
}
