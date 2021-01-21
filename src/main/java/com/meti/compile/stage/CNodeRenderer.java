package com.meti.compile.stage;

import com.meti.compile.feature.block.BlockRenderer;
import com.meti.compile.feature.function.FunctionRenderer;
import com.meti.compile.feature.function.ReturnRenderer;
import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.feature.block.BlockRenderer.BlockRenderer_;
import static com.meti.compile.feature.function.FunctionRenderer.FunctionRenderer_;
import static com.meti.compile.feature.function.ReturnRenderer.ReturnRenderer_;

public class CNodeRenderer extends CompoundRenderer<Token> {
	public static final Renderer<Token> CNodeRenderer_ = new CNodeRenderer();

	private CNodeRenderer() {
	}

	@Override
	protected List<Renderer<Token>> listRenderers() {
		return List.of(BlockRenderer_, FunctionRenderer_, ReturnRenderer_);
	}
}
