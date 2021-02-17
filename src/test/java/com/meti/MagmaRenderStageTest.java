package com.meti;

import com.meti.declare.Declaration;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.meti.MagmaRenderStage.MagmaRenderStage_;
import static org.junit.jupiter.api.Assertions.*;

class MagmaRenderStageTest {

	@Test
	void render() throws RenderException {
		var type = IntegerType.signed(16);
		var value = Integer.Zero;
		var identity = new DefaultField(type, new Input("x"), value);
		var node = new Declaration(identity);
		var list = Collections.<Token>singletonList(node);
		var actual = MagmaRenderStage_.render(list);
		assertEquals("signed int x=0;", actual);
	}
}