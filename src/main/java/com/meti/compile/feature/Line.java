package com.meti.compile.feature;

import com.meti.api.core.EF1;
import com.meti.compile.token.Node;

import java.util.Objects;

public class Line implements Node {
	private final Node value;

	private Line(Node value) {
		this.value = value;
	}

	public static Line Line(Node value) {
		return new Line(value);
	}

	@Override
	public <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		return Line(mapper.apply(value));
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
}
