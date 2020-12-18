package com.meti.compile.feature.scope;

import com.meti.api.core.EF1;
import com.meti.compile.feature.LeafNode;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;

import java.util.Objects;

public class Declaration implements LeafNode {
	private final Field field;

	private Declaration(Field field) {
		this.field = field;
	}

	public static Declaration Declaration(Field field) {
		return new Declaration(field);
	}

	@Override
	public <E extends Exception> Node mapByFields(EF1<Field, Field, E> mapper) throws E {
		return Declaration(mapper.apply(field));
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

	@Override
	public boolean is(Group group) {
		return group == Group.Declaration;
	}
}
