package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;

import java.util.Objects;

public class Declaration implements Node {
	private final Field field;

	public Declaration(Field field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "Declaration{" +
		       "field=" + field +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Declaration that = (Declaration) o;
		return Objects.equals(field, that.field);
	}

	@Override
	public int hashCode() {
		return Objects.hash(field);
	}

	@Override
	public String render() {
		return field.render() + ";";
	}
}
