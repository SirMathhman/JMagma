package com.meti.compile.feature.function;

import com.meti.compile.token.AbstractToken;
import com.meti.compile.token.Content;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.function.ReturnRenderer.*;
import static org.junit.jupiter.api.Assertions.*;

class ReturnRendererTest {

	@Test
	void render() {
		var value = new Content("0");
		var node = new Return(value);
		assertEquals("return 0;", ReturnRenderer_.render(node)
				.orElseThrow()
				.apply(AbstractToken.Query.Value)
				.asString());
	}
}