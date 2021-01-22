package com.meti.compile.token;

import java.util.List;

public record EmptyField(List<Flag> flags,
                         String name,
                         Token type) implements Field {

	@Override
	public String findName() {
		return name;
	}

	@Override
	public Token findType() {
		return type;
	}

	@Override
	public Field withType(Token type) {
		return new EmptyField(flags, name, type);
	}

}
