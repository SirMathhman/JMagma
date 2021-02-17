package com.meti.compile.attribute;

import com.meti.compile.token.Field;

public class FieldAttribute implements Attribute {
	private final Field field;

	public FieldAttribute(Field field) {
		this.field = field;
	}

	@Override
	public Field computeField() throws AttributeException {
		return field;
	}
}
