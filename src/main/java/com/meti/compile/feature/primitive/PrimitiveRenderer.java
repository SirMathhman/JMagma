package com.meti.compile.feature.primitive;

import com.meti.compile.render.RenderException;
import com.meti.compile.render.Renderer;
import com.meti.compile.token.Field;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.output.CharOutput;
import com.meti.compile.token.output.TokenOutput;
import com.meti.compile.token.output.Output;
import com.meti.compile.token.output.StringOutput;
import com.meti.core.F1E1;

import java.util.Optional;

import static com.meti.compile.token.output.EmptyOutput.EmptyOutput_;

public abstract class PrimitiveRenderer implements Renderer<Field> {
	@Override
	public Optional<Output> render(Field field) throws RenderException {
		try {
			return field.testTypeE1(this::isPrimitive) ?
					Optional.of(renderValid(field)) :
					Optional.empty();
		} catch (AttributeException e) {
			throw new RenderException(e);
		}
	}

	protected abstract boolean isPrimitive(Token type) throws AttributeException;

	private Output renderValid(Field field) throws RenderException {
		var header = renderHeader(field);
		var footer = renderFooter(field);
		return footer.prepend(new StringOutput(header));
	}

	private Output renderFooter(Field field) {
		return field.applyToValue(TokenOutput::new)
				.map(output -> output.prepend(new CharOutput('=')))
				.orElse(EmptyOutput_);
	}

	private String renderHeader(Field field) throws RenderException {
		F1E1<Input, String, RenderException> applyToHeader = value -> {
			var before = field.applyToTypeE1(this::computeString) + " ";
			return before + value.format("%s");
		};
		return field.applyToNameE1(applyToHeader);
	}

	protected abstract String computeString(Token type) throws RenderException;
}
