package com.meti.compile.feature.function;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequence;
import com.meti.compile.token.*;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public final class Implementation extends AbstractToken {
	private final Field identity;
	private final List<Field> parameters;
	private final Token body;

	public Implementation(Field identity, List<Field> parameters, Token body) {
		this.identity = identity;
		this.parameters = parameters;
		this.body = body;
	}

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
			case Parameters -> new Implementation(identity, JavaLists.toJava(attribute.asFieldList()), body);
			case Body -> new Implementation(identity, parameters, attribute.asToken());
			default -> this;
		};
	}

	@Override
	public Sequence<Query> list(Attribute.Type type) {
		return JavaLists.fromJava(list1(type));
	}

	private List<Query> list1(Attribute.Type type) {
		return switch (type) {
			case Field_ -> singletonList(Query.Identity);
			case FieldList -> singletonList(Query.Parameters);
			case Node -> singletonList(Query.Body);
			default -> emptyList();
		};
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity, parameters, body);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Implementation) obj;
		var sameIdentity = Objects.equals(this.identity, that.identity);
		var sameParameters = Objects.equals(this.parameters, that.parameters);
		var sameBody = Objects.equals(this.body, that.body);
		return sameIdentity && sameParameters && sameBody;
	}

	@Override
	public String toString() {
		return "Implementation[" +
		       "identity=" + identity + ", " +
		       "parameters=" + parameters + ", " +
		       "body=" + body + ']';
	}

}
