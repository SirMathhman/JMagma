package com.meti.compile.feature.scope;

import com.meti.api.core.EF1;
import com.meti.compile.feature.LeafNode;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;

import java.util.Objects;
import java.util.Optional;

public record Declaration(Field identity) implements LeafNode {
	@Override
	public <E extends Exception> Node mapByFields(EF1<Field, Field, E> mapper) throws E {
		return new Declaration(mapper.apply(identity));
	}

	@Override
	public Optional<Field> findIdentity() {
		return Optional.of(identity);
	}

	@Override
	public String toString() {
		return "Declaration{" +
		       "field=" + identity +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Declaration that = (Declaration) o;
		return Objects.equals(identity, that.identity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public String render() {
		return identity.render() + ";";
	}

	@Override
	public boolean is(Group group) {
		return group == Group.Declaration;
	}
}
