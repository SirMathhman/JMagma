package com.meti.compile.feature.structure;

import com.meti.api.java.collect.JavaLists;
import com.meti.compile.token.*;

import java.util.List;

public final class Structure extends AbstractToken {
	private final String name;
	private final List<Field> members;

	public Structure(String name, List<Field> members) {
		this.name = name;
		this.members = members;
	}

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
		return query == Query.Members ? new Structure(name, JavaLists.toJava(attribute.asFieldList())) : this;
	}

}
