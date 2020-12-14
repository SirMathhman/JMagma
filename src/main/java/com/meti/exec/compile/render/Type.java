package com.meti.exec.compile.render;

import com.meti.api.core.Equatable;
import com.meti.api.core.Option;

public interface Type extends Renderable, Equatable<Type> {
	Option<String> render(String name);

	Option<String> findContent();

	@Override
	default boolean equalsTo(Type other) {
		return findGroup().equals(other.findGroup()) &&
		       findContent().equalsTo(other.findContent()) &&
		       render().equalsTo(other.render());
	}

	Group findGroup();

	enum Group {
		Content
	}
}
