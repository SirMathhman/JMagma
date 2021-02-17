package com.meti.compile.app;

import com.meti.compile.feature.integer.IntegerTypeRenderer;
import com.meti.compile.render.CompoundRenderer;
import com.meti.compile.render.Renderer;
import com.meti.compile.token.Field;

import java.util.Collections;
import java.util.List;

public class MagmaTypeRenderer extends CompoundRenderer<Field> {
	public static final Renderer<Field> MagmaTypeRenderer_ = new MagmaTypeRenderer();

	public MagmaTypeRenderer() {
	}

	@Override
	protected List<Renderer<Field>> streamRenderers() {
		return Collections.singletonList(IntegerTypeRenderer.IntegerRenderer_);
	}
}
