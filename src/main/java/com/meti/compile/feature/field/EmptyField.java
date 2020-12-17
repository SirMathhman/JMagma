package com.meti.compile.feature.field;

import com.meti.compile.feature.Type;

import java.util.Objects;
import java.util.Set;

public class EmptyField implements Field {
	protected final String name;
	private final Type type;
	private final Set<Flag> flags;

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

	private EmptyField(Set<Flag> flags, String name, Type type) {
		this.name = name;
		this.type = type;
		this.flags = flags;
	}

	protected static Field EmptyField(Set<Flag> flags, String name, Type type) {
		return new EmptyField(flags, name, type);
	}

	@Override
	public String render() {
		return type.render(name);
	}
}
