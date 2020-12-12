package com.meti.exec.compile.render.feature.primitive;

import com.meti.api.core.Primitives;
import com.meti.exec.compile.render.Node;

public class Integer implements Node {
	private final int value;

	public Integer(int value) {
		this.value = value;
	}

	@Override
	public String render() {
		return Primitives.valueOfInt(value);
	}
}
