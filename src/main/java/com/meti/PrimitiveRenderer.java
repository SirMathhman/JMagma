package com.meti;

import java.util.Optional;

import static com.meti.EmptyOutput.EmptyOutput_;

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
		return footer.prependString(header);
	}

	private Output renderFooter(Field field) {
		return field.applyToValue(TokenOutput::TokenOutput)
				.map(output -> output.prependChar('='))
				.orElse(EmptyOutput_)
				.appendChar(';');
	}

	private String renderHeader(Field field) throws RenderException {
		F1E1<String, String, RenderException> renderHeader = s -> {
				var content = field.applyToTypeE1(this::computeString);
				return content + " " + s;
		};
		return field.applyToNameE1(renderHeader);
	}

	protected abstract String computeString(Token type) throws RenderException;
}
