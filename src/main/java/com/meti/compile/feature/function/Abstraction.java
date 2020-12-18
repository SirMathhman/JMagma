package com.meti.compile.feature.function;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;

import java.util.List;
import java.util.Objects;

public class Abstraction extends Function {
	private Abstraction(Field identity, List<Field> parameters) {
		super(identity, parameters);
	}

	static Abstraction Abstraction(Field identity, List<Field> parameters) {
		return new Abstraction(identity, parameters);
	}

	@Override
	protected Node copy(Field identity, List<Field> parameters) {
		return new Abstraction(identity, parameters);
	}

	@Override
	public String toString() {
		return "Function{" +
		       "identity=" + identity +
		       ", parameters=" + parameters +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Function function = (Function) o;
		return Objects.equals(identity, function.identity) &&
		       Objects.equals(parameters, function.parameters);
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity, parameters);
	}

	@Override
	protected String complete(String header) {
		return header + ";";
	}
}
