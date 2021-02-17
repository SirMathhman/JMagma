package com.meti.compile.token;

import com.meti.core.F1;
import com.meti.core.F1E1;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultField implements Field {
	private final List<Flag> flags;
	private final Token type;
	private final Token value;
	private final Input name;

	public DefaultField(List<Flag> flags, Token type, Input name, Token value) {
		this.type = type;
		this.value = value;
		this.name = name;
		this.flags = flags;
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
		return this.name.is(name);
	}

	@Override
	public <E extends Exception> Field mapByType(F1E1<Token, Token, E> mapper) throws E {
		return new DefaultField(flags, mapper.apply(type), name, value);
	}

	@Override
	public <E extends Exception> Field mapByValue(F1E1<Token, Token, E> mapper) throws E {
		return new DefaultField(flags, type, name, mapper.apply(value));
	}

	@Override
	public <E extends Exception> boolean testTypeE1(F1E1<Token, Boolean, E> predicate) throws E {
		return predicate.apply(type);
	}

	@Override
	public Field withType(Token type) {
		return new DefaultField(flags, type, name, value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(flags, type, value, name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DefaultField that = (DefaultField) o;
		return Objects.equals(flags, that.flags) && Objects.equals(type, that.type) && Objects.equals(value, that.value) && Objects.equals(name, that.name);
	}

	@Override
	public String toString() {
		var joinedFlags = joinFlags();
		return "{\"flags\":%s,\"type\":%s,\"name\":\"%s\",\"value\":%s}".formatted(joinedFlags, type, name, value);
	}

	private String joinFlags() {
		return flags.stream()
				.map(Flag::name)
				.map(String::toLowerCase)
				.collect(Collectors.joining(",", "[", "]"));
	}
}
