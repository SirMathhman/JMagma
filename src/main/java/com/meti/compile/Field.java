package com.meti.compile;

import java.util.Set;

public abstract class Field implements Node {
	protected final String name;
	protected final Type type;
	protected final Set<Flag> flags;

	protected Field(Set<Flag> flags, String name, Type type) {
		this.name = name;
		this.type = type;
		this.flags = flags;
	}

	enum Flag {
		CONST,
		LET
	}
}
