package com.meti;

import com.meti.render.CompoundRenderer;
import com.meti.render.Renderer;
import com.meti.token.Token;

import java.util.List;

import static com.meti.declare.DeclarationRenderer.DeclarationRenderer_;
import static com.meti.integer.IntegerRenderer.IntegerRenderer_;

public class MagmaNodeRenderer extends CompoundRenderer<Token> {
	public static final Renderer<Token> MagmaNodeRenderer_ = new MagmaNodeRenderer();

	private MagmaNodeRenderer() {
	}

	@Override
	protected List<Renderer<Token>> streamRenderers() {
		return List.of(DeclarationRenderer_, IntegerRenderer_);
	}
}
