package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.api.core.Equatable;
import com.meti.api.core.Option;
import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.Renderable;
import com.meti.exec.compile.render.Type;

public interface Field extends Renderable, Equatable<Field> {
	boolean isNamed(String name);

	boolean isTyped(Type type);

	boolean hasFlags(Set<Flag, ?> flags);

	Option<Node> findDefaultValue();

	enum Flag {
		CONST
	}
}
