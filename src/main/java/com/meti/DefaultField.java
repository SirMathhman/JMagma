package com.meti;

import java.util.Objects;
import java.util.Optional;

public class DefaultField implements Field {
	private final Token type;
	private final String name;
	private final Token value;


	public DefaultField(Token type, String name, Token value) {
		this.type = type;
		this.name = name;
		this.value = value;
	}

	@Override
	public <R> R applyToName(F1<String, R> mapper) {
		return mapper.apply(name);
	}

	@Override
	public <R, E extends Exception> R applyToNameE1(F1E1<String, R, E> mapper) throws E {
		return mapper.apply(name);
	}

	@Override
	public <R> R applyToType(F1<Token, R> mapper) {
		return mapper.apply(type);
	}

	@Override
	public <R, E extends Exception> R applyToTypeE1(F1E1<Token, R, E> mapper) throws E {
		return mapper.apply(type);
	}

	@Override
	public <R> Optional<R> applyToValue(F1<Token, R> mapper) {
		return Optional.of(mapper.apply(value));
	}

	@Override
	public <R, E extends Exception> Optional<R> applyToValueE1(F1E1<Token, R, E> mapper) throws E {
		return Optional.of(mapper.apply(value));
	}

	@Override
	public boolean isNamed(String name) {
		return this.name.equals(name);
	}

	@Override
	public boolean testType(F1<Token, Boolean> predicate) {
		return predicate.apply(type);
	}

	@Override
	public <E extends Exception> boolean testTypeE1(F1E1<Token, Boolean, E> predicate) throws E {
		return predicate.apply(type);
	}

	@Override
	public Field withType(Token type) {
		return new DefaultField(type, name, value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, name, value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DefaultField that = (DefaultField) o;
		var sameType = Objects.equals(type, that.type);
		var sameName = Objects.equals(name, that.name);
		var sameValue = Objects.equals(value, that.value);
		return sameType && sameName && sameValue;
	}
}
