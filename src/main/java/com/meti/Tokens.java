package com.meti;

import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;

public class Tokens {
	public Tokens() {
	}

	public static boolean is(Token token, Token.Type type) throws AttributeException {
		return token.apply(Attribute.Name.Type) == type;
	}
}
