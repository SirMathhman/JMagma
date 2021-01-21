package com.meti.compile.token;

public class Tokens {
	public static String createContent(Token token) {
		throw new UnsupportedOperationException();
	}

	public static boolean is(Token token, GroupAttribute group) {
		return token.apply(AbstractToken.Query.Group) == group;
	}
}
