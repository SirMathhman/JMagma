package com.meti.compile;

import java.util.Set;

public class ValueField extends Field {
	private final Node value;

	private ValueField(Set<Flag> flags, String name, Type type, Node value) {
		super(flags, name, type);
		this.value = value;
	}

	public static ValueField ValueField(Set<Flag> flags, String name, Type type, Node value) {
		return new ValueField(flags, name, type, value);
	}

	@Override
	public String render() {
		return type.render(name) + "=" + value.render();
	}
}
