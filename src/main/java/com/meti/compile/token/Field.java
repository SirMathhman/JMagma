package com.meti.compile.token;

public interface Field {
	String findName();

	Token findType();

	Field withType(Token type);

	enum Flag {
		CONST,
		DEF,
		IN,
		LET,
		OUT
	}
}
