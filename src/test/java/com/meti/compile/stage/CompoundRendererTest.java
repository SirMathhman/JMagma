package com.meti.compile.stage;

import com.meti.compile.feature.function.Return;
import com.meti.compile.feature.primitive.Integer;
import com.meti.compile.token.Parents;
import com.meti.compile.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.feature.block.BlockRenderer.BlockRenderer_;
import static com.meti.compile.feature.function.FunctionRenderer.FunctionRenderer_;
import static com.meti.compile.feature.function.ReturnRenderer.ReturnRenderer_;
import static org.junit.jupiter.api.Assertions.*;

class CompoundRendererTest {
	@Test
	void render() {
		var value = new Integer("0");
		var node = new Return(value);
		var expected = Parents.format("return %t;").format(value).complete();
		var actual = new Impl().render(node).orElseThrow();
		assertEquals(expected, actual);
	}

	private static class Impl extends CompoundRenderer<Token> {
		@Override
		protected List<Renderer<Token>> listRenderers() {
			return List.of(BlockRenderer_, ReturnRenderer_);
		}
	}
}