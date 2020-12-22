package com.meti.compile.feature.function;

import com.api.core.EF1;
import com.meti.compile.feature.Node;

import java.util.Objects;

public class Return implements Node{
	private final Node value;

	private Return(Node value) {
		this.value = value;
	}

	public static Return Return(Node value) {
		return new Return(value);
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
		return Return(mapper.apply(value));
	}

	@Override
	public String render() {
		return "return " + value.render() + ";";
	}
}
