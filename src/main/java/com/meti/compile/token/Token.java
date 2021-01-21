package com.meti.compile.token;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Token {

	default Attribute apply(Query query) {
		throw new IllegalArgumentException();
	}

	default Token copy(Query query, Attribute attribute) {
		return this;
	}

	default List<Query> list(Attribute.Type type) {
		return Collections.emptyList();
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
