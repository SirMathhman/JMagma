package com.meti.compile.token;

import com.meti.core.F1;
import com.meti.core.F1E1;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultField extends AbstractField implements Field {
	private final Token value;

	public DefaultField(List<Flag> flags, Input name, Token type, Token value) {
		super(flags, name, type);
		this.value = value;
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
	public <E extends Exception> Field copyByType(Token newType) throws E {
		return new DefaultField(flags, name, newType, value);
	}

	@Override
	public <E extends Exception> Field mapByValue(F1E1<Token, Token, E> mapper) throws E {
		return new DefaultField(flags, name, type, mapper.apply(value));
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
		return Objects.equals(flags, that.flags) &&
		       Objects.equals(type, that.type) &&
		       Objects.equals(value, that.value) &&
		       Objects.equals(name, that.name);
	}

	@Override
	public String toString() {
		var joinedFlags = joinFlags();
		var format = "{\"flags\":%s,\"type\":%s,\"name\":\"%s\",\"value\":%s}";
		return format.formatted(joinedFlags, type, name, value);
	}
}
