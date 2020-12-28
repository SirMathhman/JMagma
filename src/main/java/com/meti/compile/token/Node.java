package com.meti.compile.token;

import com.meti.api.core.EF1;
import com.meti.api.core.F1;
import com.meti.api.core.Option;
import com.meti.compile.feature.field.Field;
import com.meti.compile.script.Script;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.meti.api.core.None.None;

public interface Node extends Renderable {
	default <T> List<T> applyToChildren(F1<Node, T> mapper) {
		return Collections.emptyList();
	}

	default <T, E extends Exception> List<T> applyToChildrenExceptionally(EF1<Node, T, E> mapper) throws E {
		return Collections.emptyList();
	}

	default <E extends Exception> Node mapByTypes(EF1<Type, Type, E> mapper) {
		return this;
	}

	default Node mapByFields(F1<Field, Field> mapper) {
		return this;
	}

	default <E extends Exception> Node mapByFieldsExceptionally(EF1<Field, Field, E> mapper) throws E {
		return this;
	}

	default Node mapByChildren(F1<Node, Node> mapper) {
		return this;
	}

	default <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		return this;
	}

	default Optional<String> findContent() {
		return Optional.empty();
	}

	default Option<Script> findScript() {
		return None();
	}

	default boolean is(Group group) {
		return false;
	}

	Option<Field> findIdentity();

	enum Group {
		Content,
		Declaration,
		Function,
		Structure,
		Invocation,
		Block,
		Import,
		Directive,
		Integer, Return, Field
	}
}
