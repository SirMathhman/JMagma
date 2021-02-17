package com.meti;

import java.util.List;

import static com.meti.MagmaNodeRenderer.MagmaNodeRenderer_;
import static com.meti.MagmaTypeRenderer.MagmaTypeRenderer_;

public class MagmaRenderStage {
	public static final MagmaRenderStage MagmaRenderStage_ = new MagmaRenderStage();

	public MagmaRenderStage() {
	}

	private RenderException invalidate(Object value) {
		var format = "No valid renderer exists for: %s";
		var message = format.formatted(value);
		return new RenderException(message);
	}

	private String postRender(Output content) throws RenderException {
		return content.replaceField(field -> render(MagmaTypeRenderer_, field))
				.replaceNode(token -> render(MagmaNodeRenderer_, token))
				.replaceType(this::renderType)
				.compute();
	}

	public String render(List<Token> tokens) throws RenderException {
		var buffer = new StringBuilder();
		for (Token token : tokens) {
			buffer.append(render(MagmaNodeRenderer_, token));
		}
		return buffer.toString();
	}

	private <T> String render(Renderer<T> renderer, T t) throws RenderException {
		var optional = renderer.render(t);
		var output = optional.orElseThrow(() -> invalidate(t));
		return postRender(output);
	}

	private String renderType(Token token) throws RenderException {
		throw new RenderException("Cannot render.");
	}
}
