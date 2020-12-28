package com.meti.compile.token;

import com.meti.api.core.*;
import com.meti.compile.feature.field.Field;
import com.meti.compile.script.Script;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.meti.api.core.None.None;

public interface Node extends Renderable {
	default <T, E extends Exception> List<T> applyToChildren(EF1<Node, T, E> mapper) throws E {
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

	default Node mapByChildren2(Function<Node, Node> mapper) {
		try {
			return mapByChildrenExceptionally((EF1<Node, Node, Exception>) mapper::apply);
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

	default Option<Script> findScript() {
		return None();
	}

	default boolean is(Group group) {
		return false;
	}

	@Deprecated
	default Optional<Field> findIdentity2() {
		return Optional.empty();
	}

	default Option<Field> findIdentity() {
		return findIdentity2()
				.map(Some::Some)
				.orElseGet(None::None);
	}

	enum Group {
		Content,
		Declaration,
		Function,
		Structure,
		Invocation,
		Block,
		Import,
		Directive,
		Integer, Field
	}
}
