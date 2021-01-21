package com.meti.compile.feature.function;

import com.meti.compile.token.*;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public record Implementation(Field identity, List<Field> parameters, Token body) implements Token {
	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Implementation;
			case Identity -> new FieldAttribute(identity);
			case Parameters -> new FieldListAttribute(parameters);
			case Body -> new TokenAttribute(body);
			default -> throw new UnsupportedOperationException("Unknown query: " + query);
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return switch (query) {
			case Identity -> new Implementation(attribute.asField(), parameters, body);
			case Parameters -> new Implementation(identity, attribute.asFieldList(), body);
			case Body -> new Implementation(identity, parameters, attribute.asToken());
			default -> this;
		};
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return switch (type) {
			case Field_ -> singletonList(Query.Identity);
			case FieldList -> singletonList(Query.Parameters);
			case Node -> singletonList(Query.Body);
			default -> emptyList();
		};
	}
}
