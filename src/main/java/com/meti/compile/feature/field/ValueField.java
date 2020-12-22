package com.meti.compile.feature.field;

import com.api.core.EF1;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.Type;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class ValueField implements Field {
	private final String name;
	private final Type type;
	private final Set<Flag> flags;
	private final Node value;

	private ValueField(Set<Flag> flags, String name, Type type, Node value) {
		this.name = name;
		this.type = type;
		this.flags = flags;
		this.value = value;
	}

	public static ValueField ValueField(Set<Flag> flags, String name, Type type, Node value) {
		return new ValueField(flags, name, type, value);
	}

	@Override
	public String toString() {
		return "ValueField{" +
		       "name='" + name + '\'' +
		       ", type=" + type +
		       ", flags=" + flags +
		       ", value=" + value +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ValueField that = (ValueField) o;
		return Objects.equals(name, that.name) &&
		       Objects.equals(type, that.type) &&
		       Objects.equals(flags, that.flags) &&
		       Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type, flags, value);
	}

	@Override
	public String render() {
		return type.render(name) + "=" + value.render();
	}

	@Override
	public <E extends Exception> Field mapByType(EF1<Type, Type, E> mapper) throws E {
		return ValueField(flags, name, mapper.apply(type), value);
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
