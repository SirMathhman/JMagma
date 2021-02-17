package com.meti.primitive;

import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;
import com.meti.token.Token;

public class ImplicitType implements Token {
	public static final Token ImplicitType_ = new ImplicitType();

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		if (name == Attribute.Name.Type) return Token.Type.Implicit;
		throw new AttributeException();
	}
}
