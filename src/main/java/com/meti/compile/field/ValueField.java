package com.meti.compile.field;

import com.meti.compile.Node;
import com.meti.compile.Type;

import java.util.Objects;
import java.util.Set;

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
}
