package com.meti.compile.feature.field;

import com.meti.api.core.EF1;
import com.meti.compile.token.Type;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class EmptyField implements Field {
	protected final String name;
	private final Type type;
	private final Set<Flag> flags;

	private EmptyField(Set<Flag> flags, String name, Type type) {
		this.name = name;
		this.type = type;
		this.flags = flags;
	}

	protected static Field EmptyField(Set<Flag> flags, String name, Type type) {
		return new EmptyField(flags, name, type);
	}

	@Override
	public String toString() {
		return "EmptyField{" +
		       "name='" + name + '\'' +
		       ", type=" + type +
		       ", flags=" + flags +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmptyField that = (EmptyField) o;
		return Objects.equals(name, that.name) &&
		       Objects.equals(type, that.type) &&
		       Objects.equals(flags, that.flags);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type, flags);
	}

	@Override
	public String render() {
		return type.render(name);
	}

	@Override
	public <E extends Exception> Field mapByType(EF1<Type, Type, E> mapper) throws E {
		return EmptyField(flags, name, mapper.apply(type));
	}

	@Override
	public <T> T applyToType(Function<Type, T> mapper) {
		return mapper.apply(type);
	}

	@Override
	public <T> T applyToName(Function<String, T> mapper) {
		return mapper.apply(name);
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public boolean isFlagged(Flag flag) {
		return flags.contains(flag);
	}
}
