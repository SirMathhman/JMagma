package com.meti.exec.compile.render;

import com.meti.api.core.Equatable;
import com.meti.api.core.Option;

public interface Node extends Renderable, Equatable<Node> {
	Option<String> findContent();

	@Override
	default boolean equalsTo(Node other) {
		return findContent().equalsTo(other.findContent()) &&
		       render().equalsTo(other.render());
	}

	@Override
	default String asString() {
		return "";
	}
}
