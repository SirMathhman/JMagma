package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.api.collect.string.Strings;
import com.meti.exec.compile.render.Type;

public abstract class AbstractField<F> implements Field {
	protected final String name;
	protected final Type type;
	protected final Set<Field.Flag, ?> flags;

	public AbstractField(Set<Flag, ?> flags, String name, Type type) {
		this.name = name;
		this.type = type;
		this.flags = flags;
	}

	@Override
	public boolean isNamed(String name) {
		return Strings.equalsTo(this.name, name);
	}

	@Override
	public boolean isTyped(Type type) {
		return this.type.equalsTo(type);
	}

	@Override
	public boolean hasFlags(Set<Flag, ?> flags) {
		return this.flags.intersects(flags);
	}
}
