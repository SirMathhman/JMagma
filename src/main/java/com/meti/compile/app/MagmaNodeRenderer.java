package com.meti.compile.app;

import com.meti.compile.render.CompoundRenderer;
import com.meti.compile.render.Renderer;
import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.feature.assign.AssignmentRenderer.AssignmentRenderer_;
import static com.meti.compile.feature.declare.DeclarationRenderer.DeclarationRenderer_;
import static com.meti.compile.feature.integer.IntegerRenderer.IntegerRenderer_;

public class MagmaNodeRenderer extends CompoundRenderer<Token> {
	public static final Renderer<Token> MagmaNodeRenderer_ = new MagmaNodeRenderer();

	private MagmaNodeRenderer() {
	}

	@Override
	protected List<Renderer<Token>> streamRenderers() {
		return List.of(AssignmentRenderer_, DeclarationRenderer_, IntegerRenderer_);
	}
}
