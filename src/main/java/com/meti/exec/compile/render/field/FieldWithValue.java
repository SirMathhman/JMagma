package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.RenderException;
import com.meti.exec.compile.render.Type;

public class FieldWithValue extends AbstractField<FieldWithValue> {
	private final Node<?> value;

	public FieldWithValue(Set<Flag, ?> flags, String name, Type type, Node<?> value) {
		super(flags, name, type);
		this.value = value;
	}

	@Override
	public String render() throws RenderException {
		return type.render(name) + "=" + value.render();
	}

	@Override
	public int compareTo(FieldWithValue other) {
		return -1;
	}

}
