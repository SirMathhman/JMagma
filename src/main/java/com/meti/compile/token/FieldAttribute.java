package com.meti.compile.token;

public record FieldAttribute(Field field) implements Attribute {
	@Override
	public Field asField() {
		return field;
	}
}
