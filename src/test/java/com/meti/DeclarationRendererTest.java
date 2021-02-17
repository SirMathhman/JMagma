package com.meti;

import com.meti.declare.Declaration;
import com.meti.integer.Integer;
import com.meti.integer.IntegerType;
import com.meti.output.CharOutput;
import com.meti.output.FieldOutput;
import org.junit.jupiter.api.Test;

import static com.meti.declare.DeclarationRenderer.DeclarationRenderer_;
import static com.meti.output.ListOutput.ListOutput;
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