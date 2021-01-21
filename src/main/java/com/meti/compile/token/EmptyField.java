package com.meti.compile.token;

import java.util.List;
import java.util.Optional;

public record EmptyField(List<Flag> flags,
                         String name,
                         Token type) implements Field {
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
		return Optional.empty();
	}

	@Override
	public Field withType(Token type) {
		return new EmptyField(flags, name, type);
	}

	@Override
	public Optional<Field> withValue(Token value) {
		return Optional.of(new EmptyField(flags, name, type));
	}
}
