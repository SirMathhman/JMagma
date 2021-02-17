package com.meti.primitive;

import com.meti.token.Field;
import com.meti.token.Token;
import com.meti.attribute.AttributeException;
import com.meti.core.F1E1;
import com.meti.output.CharOutput;
import com.meti.output.NodeOutput;
import com.meti.output.Output;
import com.meti.output.StringOutput;
import com.meti.render.RenderException;
import com.meti.render.Renderer;

import java.util.Optional;

import static com.meti.output.EmptyOutput.EmptyOutput_;

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
		return field.applyToValue(NodeOutput::new)
				.map(output -> output.prepend(new CharOutput('=')))
				.orElse(EmptyOutput_);
	}

	private String renderHeader(Field field) throws RenderException {
		F1E1<String, String, RenderException> renderHeader = s -> {
			var content = field.applyToTypeE1(this::computeString);
			return content + " " + s;
		};
		return field.applyToNameE1(value -> renderHeader.apply(value.getContent()));
	}

	protected abstract String computeString(Token type) throws RenderException;
}
