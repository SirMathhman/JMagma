package com.meti;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.MagmaRenderStage.MagmaRenderStage_;
import static org.junit.jupiter.api.Assertions.*;

class MagmaRenderStageTest {

	@Test
	void render() throws RenderException {
		var type = IntegerType.signed(16);
		var value = Integer.Zero;
		var identity = new DefaultField(type, "x", value);
		var node = new Declaration(identity);
		var list = Collections.<Token>singletonList(node);
		var actual = MagmaRenderStage_.render(list);
		assertEquals("signed int x=0;", actual);
	}
}