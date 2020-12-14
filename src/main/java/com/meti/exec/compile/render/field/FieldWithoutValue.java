package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.exec.compile.render.RenderException;
import com.meti.exec.compile.render.Type;

public class FieldWithoutValue extends AbstractField<FieldWithoutValue> {
	public FieldWithoutValue(Set<Flag, ?> flags, String name, Type type) {
		super(flags, name, type);
	}

	@Override
	public String render() throws RenderException {
		return type.render(name);
	}

	@Override
	public int compareTo(FieldWithoutValue other) {
		return 0;
	}
}
