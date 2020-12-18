package com.meti.compile.feature.function;

import com.meti.api.core.EF1;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;

import java.util.List;
import java.util.Objects;

public class Implementation extends Function {
	private final Node value;

	private Implementation(Field identity, List<Field> parameters, Node value) {
		super(identity, parameters);
		this.value = value;
	}

	public static Function Implementation(Field identity, List<Field> parameters, Node value) {
		return new Implementation(identity, parameters, value);
	}



	@Override
	public String toString() {
		return "Function{" +
		       "identity=" + identity +
		       ", parameters=" + parameters +
		       ", value=" + value +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Implementation that = (Implementation) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public <E extends Exception> Node mapByChildren(EF1<Node, Node, E> mapper) throws E {
		return new Implementation(identity, parameters, mapper.apply(value));
	}

	@Override
	protected Node copy(Field identity, List<Field> parameters) {
		return new Implementation(identity, parameters, value);
	}

	@Override
	protected String complete(String header) {
		return header + value.render();
	}
}
