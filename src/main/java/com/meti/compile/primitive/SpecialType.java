package com.meti.compile.primitive;

import com.meti.compile.token.Token;
import com.meti.compile.attribute.Attribute;
import com.meti.compile.attribute.AttributeException;

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
