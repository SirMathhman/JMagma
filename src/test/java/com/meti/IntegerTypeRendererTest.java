package com.meti;

import com.meti.integer.Integer;
import com.meti.integer.IntegerType;
import com.meti.output.CharOutput;
import com.meti.output.NodeOutput;
import com.meti.output.Output;
import com.meti.output.StringOutput;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.meti.integer.IntegerTypeRenderer.IntegerRenderer_;
import static com.meti.output.ListOutput.ListOutput;
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