package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.Type;

import static com.meti.api.core.None.None;

public class FieldWithoutValue extends AbstractField<FieldWithoutValue> {
	public FieldWithoutValue(Set<Flag, ?> flags, String name, Type type) {
		super(flags, name, type);
	}

	@Override
	public Option<String> render() {
		return null;
	}

	@Override
	public Option<Node> findDefaultValue() {
		return None();
	}

	@Override
	public boolean equalsTo(Field other) {
		return other.isNamed(name) &&
		       other.isTyped(type) &&
		       other.hasFlags(flags);
	}
}
