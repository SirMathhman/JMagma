package com.meti;

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
