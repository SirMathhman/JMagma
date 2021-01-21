package com.meti.compile.token;

import java.util.List;
import java.util.Optional;

record ValuedField(List<Flag> flags,
                   String name,
                   Token type,
                   Token value) implements Field {
	@Override
	public List<Flag> findFlags() {
		return flags;
	}

	@Override
	public String findName() {
		return name;
	}

	@Override
	public Token findType() {
		return type;
	}

	@Override
	public Optional<Token> findValue() {
		return Optional.of(value);
	}

	@Override
	public Field withType(Token type) {
		return new ValuedField(flags, name, type, value);
	}

	@Override
	public Optional<Field> withValue(Token value) {
		return Optional.of(new ValuedField(flags, name, type, value));
	}
}
