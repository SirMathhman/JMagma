package com.meti.compile.token;

import java.util.List;

record ValuedField(List<Flag> flags,
                   String name,
                   Token type,
                   Token value) implements Field {

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
		return new ValuedField(flags, name, type, value);
	}

}
