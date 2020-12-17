package com.meti.compile.feature;

import java.util.Optional;

public interface Type extends Renderable {
	@Override
	default String render() {
		return render("");
	}

	String render(String value);

	default Optional<String> findContent() {
		return Optional.empty();
	}

	default boolean is(Group group) {
		return false;
	}

	enum Group {
		Content
	}
}
