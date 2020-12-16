package com.meti.exec.compile.render;

import com.meti.api.core.Equatable;
import com.meti.api.core.Option;

public interface Type extends Renderable, Equatable<Type> {
	default Option<String> render() {
		return render("");
	}

	Option<String> render(String name);

	Option<String> findContent();

	@Override
	default boolean equalsTo(Type other) {
		return findGroup().equals(other.findGroup()) &&
		       findContent().equalsTo(other.findContent()) &&
		       render().equalsTo(other.render());
	}

	Group findGroup();

	@Override
	default String asString() {
		return "";
	}

	enum Group {
		Implicit,
		Content
	}
}
