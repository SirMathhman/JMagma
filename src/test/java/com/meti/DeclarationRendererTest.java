package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.DeclarationRenderer.DeclarationRenderer_;
import static com.meti.ListOutput.ListOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeclarationRendererTest {

	@Test
	void render() throws RenderException {
		var identity = new DefaultField(IntegerType.unsigned(8), new Input("x"), Integer.Zero);
		var current = new Declaration(identity);
		var actual = DeclarationRenderer_.render(current).orElseThrow();
		var expected = ListOutput()
				.append(new FieldOutput(identity))
				.append(new CharOutput(';'));
		assertEquals(expected, actual);
	}
}