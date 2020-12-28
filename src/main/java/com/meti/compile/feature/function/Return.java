package com.meti.compile.feature.function;

import com.meti.api.core.*;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record Return(Node value) implements Node {
	@Override
	public <T> List<T> applyToChildren(F1<Node, T> mapper) {
		return Collections.singletonList(mapper.apply(value));
	}

	@Override
	public <T, E extends Exception> List<T> applyToChildrenExceptionally(EF1<Node, T, E> mapper) throws E {
		return Collections.singletonList(mapper.apply(value));
	}

	@Override
	public boolean is(Group group) {
		return group == Group.Return;
	}

	@Override
	public String toString() {
		return "Return{" +
		       "value=" + value +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Return aReturn = (Return) o;
		return Objects.equals(value, aReturn.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		return new Return(mapper.apply(value));
	}

	@Override
	public String render() {
		return "return " + value.render() + ";";
	}

	@Override
	public Option<Field> findIdentity() {
		return findIdentity2()
				.map(Some::Some)
				.orElseGet(None::None);
	}

	@Deprecated
	private Optional<Field> findIdentity2() {
		return Optional.empty();
	}
}
