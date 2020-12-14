package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.api.core.Option;
import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.Type;

public class FieldWithValue extends AbstractField<FieldWithValue> {
	private final Node<?> value;

	public FieldWithValue(Set<Flag, ?> flags, String name, Type type, Node<?> value) {
		super(flags, name, type);
		this.value = value;
	}

	@Override
	public Option<String> render() {
		return null;
	}

	@Override
	public boolean equalsTo(FieldWithValue other) {
		return false;
	}
}
