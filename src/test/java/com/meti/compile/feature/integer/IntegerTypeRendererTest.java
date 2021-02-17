package com.meti.compile.feature.integer;

import com.meti.compile.token.output.CharOutput;
import com.meti.compile.token.output.NodeOutput;
import com.meti.compile.token.output.Output;
import com.meti.compile.token.output.StringOutput;
import com.meti.compile.render.RenderException;
import com.meti.compile.token.DefaultField;
import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.meti.compile.feature.integer.IntegerTypeRenderer.IntegerRenderer_;
import static com.meti.compile.token.output.ListOutput.ListOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerTypeRendererTest {
	@Test
	void render() throws RenderException {
		var value = new Integer(420);
		var expected = Optional.of(computeOutput(value));
		var type = IntegerType.signed(16);
		var identity = new DefaultField(type, new Input("x"), value);
		var actual = IntegerRenderer_.render(identity);
		assertEquals(expected, actual);
	}

	private Output computeOutput(Integer value) throws RenderException {
		return ListOutput()
				.append(new StringOutput("signed int x"))
				.append(new CharOutput('='))
				.append(new NodeOutput(value));
	}
}