package com.meti.compile.app;

import com.meti.compile.feature.declare.Declaration;
import com.meti.compile.feature.integer.Integer;
import com.meti.compile.feature.integer.IntegerType;
import com.meti.compile.render.RenderException;
import com.meti.compile.token.DefaultField;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.meti.compile.app.MagmaRenderStage.MagmaRenderStage_;
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