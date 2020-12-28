package com.meti.compile.feature.scope;

import com.meti.api.core.EF1;
import com.meti.api.core.F1;
import com.meti.api.core.Option;
import com.meti.compile.token.Node;
import com.meti.compile.feature.field.Field;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.meti.api.core.Some.Some;

public record Declaration(Field identity) implements Node {
	@Override
	public <E extends Exception> Node mapByFieldsExceptionally(EF1<Field, Field, E> mapper) throws E {
		return new Declaration(mapper.apply(identity));
	}

	@Override
	public <T, E extends Exception> List<T> applyToChildrenExceptionally(EF1<Node, T, E> mapper) throws E {
		return identity.findValue()
				.mapExceptionally(mapper)
				.map(Collections::singletonList)
				.orElseGet(Collections::emptyList);
	}

	@Override
	public Node mapByFields(F1<Field, Field> mapper) {
		return new Declaration(mapper.apply(identity));
	}

	@Override
	public Node mapByChildren(F1<Node, Node> mapper) {
		return new Declaration(identity.mapByValue(mapper));
	}

	@Override
	public <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		return new Declaration(identity.mapByValueExceptionally(mapper));
	}

	@Override
	public Option<Field> findIdentity() {
		return Some(identity);
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
