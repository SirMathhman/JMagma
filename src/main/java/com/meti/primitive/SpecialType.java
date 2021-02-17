package com.meti.primitive;

import com.meti.token.Token;
import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;

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
