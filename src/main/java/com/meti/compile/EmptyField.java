package com.meti.compile;

import java.util.Set;

public class EmptyField extends Field {
	private EmptyField(Set<Flag> flags, String name, Type type) {
		super(flags, name, type);
	}

	protected static EmptyField EmptyField(Set<Flag> flags, String name, Type type) {
		return new EmptyField(flags, name, type);
	}

	@Override
	public String render() {
		return type.render(name);
	}
}
