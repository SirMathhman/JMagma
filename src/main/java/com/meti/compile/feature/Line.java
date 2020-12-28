package com.meti.compile.feature;

import com.meti.api.core.EF1;
import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.Objects;
import java.util.Optional;

public record Line(Node value) implements Node {
	@Override
	public <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		return new Line(mapper.apply(value));
	}

	@Override
	public String toString() {
		return "Line{" +
		       "value=" + value +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Line line = (Line) o;
		return Objects.equals(value, line.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String render() {
		return value.render() + ";";
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
