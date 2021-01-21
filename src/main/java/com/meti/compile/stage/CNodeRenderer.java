package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.List;

public class CNodeRenderer extends CompoundRenderer<Token> {
	public static final Renderer<Token> CNodeRenderer_ = new CNodeRenderer();

	private CNodeRenderer() {
	}

	@Override
	protected List<Renderer<Token>> listRenderers() {
		return List.of();
	}
}
