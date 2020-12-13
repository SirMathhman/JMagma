package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.exec.compile.render.Type;

public abstract class AbstractField<F> implements Field<F> {
	protected final String name;
	protected final Type type;
	protected final Set<Field.Flag, ?> flags;

	public AbstractField(Set<Flag, ?> flags, String name, Type type) {
		this.name = name;
		this.type = type;
		this.flags = flags;
	}
}
