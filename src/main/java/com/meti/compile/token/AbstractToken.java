package com.meti.compile.token;

import com.meti.api.java.collect.JavaList;
import com.meti.api.java.collect.JavaLists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractToken implements Token {
	@Override
	public String toString() {
		return Arrays.stream(Attribute.Type.values())
				.map(this::list)
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
