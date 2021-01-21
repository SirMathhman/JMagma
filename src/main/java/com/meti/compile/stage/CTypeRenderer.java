package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.feature.primitive.PrimitiveTypeRenderer.PrimitiveTypeRenderer_;

public class CTypeRenderer extends CompoundRenderer<Token> {
	public static final Renderer<Token> CTypeRenderer_ = new CTypeRenderer();

	private CTypeRenderer() {
	}

	@Override
	protected List<Renderer<Token>> listRenderers() {
		return List.of(PrimitiveTypeRenderer_);
	}
}
