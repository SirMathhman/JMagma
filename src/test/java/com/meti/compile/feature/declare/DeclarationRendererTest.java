package com.meti.compile.feature.declare;

import com.meti.compile.feature.integer.Integer;
import com.meti.compile.feature.integer.IntegerType;
import com.meti.compile.token.Field;
import com.meti.compile.token.RootInput;
import com.meti.compile.token.output.CharOutput;
import com.meti.compile.token.output.FieldOutput;
import com.meti.compile.render.RenderException;
import com.meti.compile.token.DefaultField;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.meti.compile.feature.declare.DeclarationRenderer.DeclarationRenderer_;
import static com.meti.compile.token.output.ListOutput.ListOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeclarationRendererTest {

	@Test
	void render() throws RenderException {
		var identity = new DefaultField(new ArrayList<Field.Flag>(), IntegerType.unsigned(8), new RootInput("x"), Integer.Zero);
		var current = new Declaration(identity);
		var actual = DeclarationRenderer_.render(current).orElseThrow();
		var expected = ListOutput()
				.append(new FieldOutput(identity))
				.append(new CharOutput(';'));
		assertEquals(expected, actual);
	}
}