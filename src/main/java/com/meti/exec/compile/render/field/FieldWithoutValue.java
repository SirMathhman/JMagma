package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.api.collect.list.ArrayList;
import com.meti.exec.compile.render.RenderException;
import com.meti.exec.compile.render.Type;

public class FieldWithoutValue extends AbstractField<FieldWithoutValue> {
	public FieldWithoutValue(Set<Flag, ?> flags, String name, Type type) {
		super(flags, name, type);
	}

	static Builder Builder() {
		return new Builder(ArrayList.empty(Flag::compareTo));
	}

	@Override
	public String render() throws RenderException {
		return type.render(name);
	}

	@Override
	public int compareTo(FieldWithoutValue other) {
		return 0;
	}

	@Override
	public int compareTo2(Object other) {
		return 0;
	}

	static record Builder(Set<Flag, ?> flags) {

	}
}
