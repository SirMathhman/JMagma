package com.meti;

import com.meti.compile.declare.Declaration;
import com.meti.compile.integer.Integer;
import com.meti.compile.integer.IntegerType;
import com.meti.compile.output.CharOutput;
import com.meti.compile.output.FieldOutput;
import com.meti.compile.render.RenderException;
import com.meti.compile.token.DefaultField;
import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import static com.meti.compile.declare.DeclarationRenderer.DeclarationRenderer_;
import static com.meti.compile.output.ListOutput.ListOutput;
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