package com.meti.compile.token;

import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;

public class Tokens {
	public Tokens() {
	}

	public static boolean is(Token token, Token.Type type) throws AttributeException {
		return token.apply(Attribute.Name.Type) == type;
	}
}
