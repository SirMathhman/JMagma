package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.api.core.Option;
import com.meti.exec.compile.render.Type;

public class FieldWithoutValue extends AbstractField<FieldWithoutValue> {
	public FieldWithoutValue(Set<Flag, ?> flags, String name, Type type) {
		super(flags, name, type);
	}

	@Override
	public Option<String> render() {
		return null;
	}

	@Override
	public boolean equalsTo(FieldWithoutValue other) {
		return false;
	}
}
