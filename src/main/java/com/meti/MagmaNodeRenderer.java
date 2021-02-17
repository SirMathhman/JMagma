package com.meti;

import java.util.List;

import static com.meti.DeclarationRenderer.DeclarationRenderer_;
import static com.meti.IntegerRenderer.IntegerRenderer_;

public class MagmaNodeRenderer extends CompoundRenderer<Token> {
	public static final Renderer<Token> MagmaNodeRenderer_ = new MagmaNodeRenderer();

	private MagmaNodeRenderer() {
	}

	@Override
	protected List<Renderer<Token>> streamRenderers() {
		return List.of(DeclarationRenderer_, IntegerRenderer_);
	}
}
