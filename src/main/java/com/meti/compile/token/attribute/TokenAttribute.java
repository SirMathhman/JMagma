package com.meti.compile.token.attribute;

import com.meti.compile.token.Token;

public class TokenAttribute implements Attribute {
	private final Token token;

	public TokenAttribute(Token token) {
		this.token = token;
	}

	@Override
	public Token computeToken() throws AttributeException {
		return token;
	}
}
