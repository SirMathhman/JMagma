package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.feature.block.BlockRenderer.BlockRenderer_;
import static com.meti.compile.feature.function.FunctionRenderer.FunctionRenderer_;
import static com.meti.compile.feature.function.ReturnRenderer.ReturnRenderer_;
import static com.meti.compile.feature.primitive.IntegerRenderer.IntegerRenderer_;

public class CNodeRenderer extends CompoundRenderer<Token> {
	public static final Renderer<Token> CNodeRenderer_ = new CNodeRenderer();

	private CNodeRenderer() {
	}

	@Override
	protected List<Renderer<Token>> listRenderers() {
		return List.of(BlockRenderer_,
				FunctionRenderer_,
				IntegerRenderer_,
				ReturnRenderer_);
	}
}
