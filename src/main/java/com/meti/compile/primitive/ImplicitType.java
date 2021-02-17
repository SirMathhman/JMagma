package com.meti.compile.primitive;

import com.meti.compile.attribute.Attribute;
import com.meti.compile.attribute.AttributeException;
import com.meti.compile.token.Token;

public class ImplicitType implements Token {
	public static final Token ImplicitType_ = new ImplicitType();

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		if (name == Attribute.Name.Type) return Token.Type.Implicit;
		throw new AttributeException();
	}
}
