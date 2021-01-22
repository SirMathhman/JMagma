package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequence;

import java.util.Collections;

public interface Token {
	default Attribute apply(Query query) {
		throw new IllegalArgumentException();
	}

	default Token copy(Query query, Attribute attribute) {
		return this;
	}

	default Sequence<Query> list(Attribute.Type type) {
		//TODO: replace stub
		return JavaLists.fromJava(Collections.emptyList());
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
