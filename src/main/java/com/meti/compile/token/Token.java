package com.meti.compile.token;

import com.meti.api.java.collect.JavaList;
import com.meti.api.magma.collect.List;

import java.util.Collections;

public interface Token {
	default Attribute apply(Query query) {
		throw new IllegalArgumentException();
	}

	default Token copy(Query query, Attribute attribute) {
		return this;
	}

	default List<Query> list(Attribute.Type type) {
		//TODO: replace stub
		return new JavaList<>(Collections.emptyList());
	}

	enum Query {
		Body,
		Group,
		Identity,
		Lines,
		Members,
		Name,
		Parameters,
		Returns,
		Type,
		Value
	}
}
