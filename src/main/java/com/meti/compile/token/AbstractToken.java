package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractToken implements Token {


	@Override
	public com.meti.api.magma.collect.List<Query> list(Attribute.Type type) {
		return list(type);
	}

	@Override
	public String toString() {
		return Arrays.stream(Attribute.Type.values())
				.map(type -> JavaLists.toJava(list(type)))
				.flatMap(List::stream)
				.map(this::queryToString)
				.collect(Collectors.joining(",", "{", "}"));
	}

	private String queryToString(Query query) {
		var queryName = query.name();
		var queryValue = apply(query);
		return "\"%s\":%s".formatted(queryName, queryValue);
	}
}
