package com.meti.compile.token;

import com.meti.api.core.EF1;

import java.util.Optional;

public interface Type extends Renderable {
	default <E extends Exception> Type mapByChildren(EF1<Type, Type, E> mapper) throws E {
		return this;
	}

	@Override
	default String render() {
		return render("").trim();
	}

	String render(String value);

	default Optional<String> findContent() {
		return Optional.empty();
	}

	default boolean is(Group group) {
		return false;
	}

	default <E extends Exception> Type mapByChildExceptionally(EF1<Type, Type, E> mapper) throws E {
		return this;
	}

	default Optional<Type> findChild() {
		return Optional.empty();
	}

	default Type replaceChild(Type replacement) {
		return this;
	}

	enum Group {
		Implicit, Content
	}
}
