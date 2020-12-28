package com.meti.compile.feature.field;

import com.meti.api.core.EF1;
import com.meti.api.core.F1;
import com.meti.api.core.Option;
import com.meti.compile.token.Node;
import com.meti.compile.token.Renderable;
import com.meti.compile.token.Type;

import java.util.function.Function;

public interface Field extends Renderable {
	<E extends Exception> Field mapByType(EF1<Type, Type, E> mapper) throws E;

	<T> T applyToType(Function<Type, T> mapper);

	<T> T applyToName(Function<String, T> mapper);

	@Deprecated
	Type type();

	Option<Type> findType();

	boolean isFlagged(Flag flag);

	Field replaceType(Type replacement);

	Option<Node> findValue();

	Field mapByValue(F1<Node, Node> mapper);

	<E extends Exception> Field mapByValueExceptionally(EF1<Node, Node, E> mapper) throws E;

	enum Flag {
		NATIVE,
		CONST,
		DEF,
		LET,
	}
}
