package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;

public class Declaration implements Node {
	private final Field field;

	public Declaration(Field field) {
		this.field = field;
	}

	@Override
	public String render() {
		return field.render() + ";";
	}
}
