package com.meti.compile.token;

public class Tokens {
	public static boolean is(Token token, GroupAttribute group) {
		return token.apply(AbstractToken.Query.Group) == group;
	}
}
