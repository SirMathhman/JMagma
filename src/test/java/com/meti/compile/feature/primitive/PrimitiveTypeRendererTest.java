package com.meti.compile.feature.primitive;

import com.meti.compile.token.Content;
import com.meti.compile.token.Pair;
import com.meti.compile.token.Token;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.primitive.PrimitiveTypeRenderer.PrimitiveTypeRenderer_;
import static org.junit.jupiter.api.Assertions.*;

class PrimitiveTypeRendererTest {

	@Test
	void render() {
		var pair = new Pair(Primitives.I16, new Content("main()"));
		var actual = PrimitiveTypeRenderer_.render(pair)
				.orElseThrow()
				.apply(Token.Query.Value)
				.asString();
		assertEquals("int main()", actual);
	}
}