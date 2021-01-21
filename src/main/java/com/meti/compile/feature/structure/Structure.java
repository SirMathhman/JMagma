package com.meti.compile.feature.structure;

import com.meti.compile.token.*;

import java.util.Collections;
import java.util.List;

public record Structure(String name, List<Field> members) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Structure;
			case Name -> new StringAttribute(name);
			case Members -> new FieldListAttribute(members);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return query == Query.Members ? new Structure(name, attribute.asFieldList()) : this;
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return type == Attribute.Type.FieldList ?
				Collections.singletonList(Query.Members) :
				Collections.emptyList();
	}
}
