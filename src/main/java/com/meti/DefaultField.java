package com.meti;

import java.util.Objects;
import java.util.Optional;

public class DefaultField implements Field {
	private final Token type;
	private final Token value;
	private final Input name;

	public DefaultField(Token type, Input name, Token value) {
		this.type = type;
		this.value = value;
		this.name = name;
	}

	@Override
	public <R> R applyToName(F1<Input, R> mapper) {
		return mapper.apply(name);
	}

	@Override
	public <R, E extends Exception> R applyToNameE1(F1E1<Input, R, E> mapper) throws E {
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
		return this.name.getContent().equals(name);
	}

	@Override
	public <E extends Exception> boolean testTypeE1(F1E1<Token, Boolean, E> predicate) throws E {
		return predicate.apply(type);
	}

	@Override
	public Field withType(Token type) {
		return new DefaultField(type, new Input(name.getContent()), value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, name.getContent(), value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DefaultField that = (DefaultField) o;
		var sameType = Objects.equals(type, that.type);
		var sameName = Objects.equals(name.getContent(), that.name.getContent());
		var sameValue = Objects.equals(value, that.value);
		return sameType && sameName && sameValue;
	}

	@Override
	public String toString() {
		return "{\"type\":%s,\"name\":\"%s\",\"value\":%s}".formatted(type, name.getContent(), value);
	}
}
