package com.meti.compile.feature;

import com.api.core.EF1;
import com.meti.compile.feature.field.Field;

import java.util.Optional;
import java.util.function.Function;

public interface Node extends Renderable {
	default <E extends Exception> Node mapByTypes(EF1<Type, Type, E> mapper) throws E {
		return this;
	}

	default <E extends Exception> Node mapByFields(EF1<Field, Field, E> mapper) throws E {
		return this;
	}

	default Node mapByChildren(Function<Node, Node> mapper) {
		try {
			return mapByChildrenExceptionally((EF1<Node, Node, Exception>) node -> mapper.apply(node));
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}

	default <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		return this;
	}

	default Optional<String> findContent() {
		return Optional.empty();
	}

	default boolean is(Group group) {
		return false;
	}

	default Optional<Field> findIdentity() {
		return Optional.empty();
	}

	enum Group {
		Content,
		Declaration, Function, Structure, Invocation, Block, Field
	}
}
