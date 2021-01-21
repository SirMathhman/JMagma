package com.meti.compile.token;

import java.util.List;

public record FieldListAttribute(List<Field> fields) implements Attribute {
	@Override
	public List<Field> asFieldList() {
		return fields;
	}
}
