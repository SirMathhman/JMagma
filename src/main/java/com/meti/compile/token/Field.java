package com.meti.compile.token;

import java.util.List;
import java.util.Optional;

public interface Field {
	List<Flag> findFlags();

	String findName();

	Token findType();

	Optional<Token> findValue();

	Field withType(Token type);

	Optional<Field> withValue(Token value);

	enum Flag {
		CONST,
		DEF,
		IN,
		LET,
		OUT
	}
}
