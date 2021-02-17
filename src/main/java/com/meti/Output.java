package com.meti;

public interface Output {
	Output append(Output output);

	default String compute() throws RenderException {
		throw new RenderException("Output isn't renderable yet.");
	}

	Output prepend(Output output);

	default Output replaceField(F1<Field, String> replacer) {
		return this;
	}

	default Output replaceNode(F1<Token, String> replacer) {
		return this;
	}

	default Output replaceType(F1<Token, String> replacer) {
		return this;
	}
}
