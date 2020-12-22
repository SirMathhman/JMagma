package com.meti.compile.feature.field;

import com.api.core.EF1;
import com.meti.compile.feature.LeafNode;
import com.meti.compile.feature.Type;

import java.util.Optional;
import java.util.function.Function;

public interface Field extends LeafNode {
	<E extends Exception> Field mapByType(EF1<Type, Type, E> mapper) throws E;

	<T> T applyToType(Function<Type, T> mapper);

	<T> T applyToName(Function<String, T> mapper);

	@Override
	default Optional<String> findContent() {
		return Optional.empty();
	}

	@Override
	default boolean is(Group group) {
		return group == Group.Field;
	}

	Type type();

	boolean isFlagged(Flag flag);

	enum Flag {
		NATIVE,
		CONST,
		DEF,
		LET,
	}
}
