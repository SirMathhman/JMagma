package com.meti.compile.feature.primitive;

import com.meti.compile.token.AbstractToken;
import com.meti.compile.token.Content;
import com.meti.compile.token.Pair;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.meti.compile.feature.primitive.PrimitiveTypeRenderer.PrimitiveTypeRenderer_;
import static org.junit.jupiter.api.Assertions.*;

class PrimitiveTypeRendererTest {

	@Test
	void render() {
		var pair = new Pair(Primitives.I16, new Content("main()"));
		var actual = PrimitiveTypeRenderer_.render(pair)
				.map(Optional::of)
				.orElseGet(Optional::empty)
				.orElseThrow()
				.apply(AbstractToken.Query.Value)
				.asString();
		assertEquals("int main()", actual);
	}
}