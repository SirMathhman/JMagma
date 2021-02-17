package com.meti;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.meti.IntegerRenderer.IntegerRenderer_;
import static com.meti.ListOutput.ListOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerRendererTest {
	@Test
	void render() throws RenderException {
		var value = new Integer(420);
		var expected = Optional.of(computeOutput(value));
		var type = IntegerType.signed(16);
		var identity = new DefaultField(type, "x", value);
		var actual = IntegerRenderer_.render(identity);
		assertEquals(expected, actual);
	}

	private Output computeOutput(Integer value) throws RenderException {
		return ((Output) ListOutput()).append(new StringOutput("signed int x")).append(new CharOutput('='))
				.append(new TokenOutput(value)).append(new CharOutput(';'));
	}
}