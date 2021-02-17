package com.meti;

public enum SpecialType implements Token {
	Void,
	Any,
	Bool;

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		if (name == Attribute.Name.Type) return Type.Special;
		throw new AttributeException();
	}
}
