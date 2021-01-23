package com.meti.compile.feature.function;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.ArrayLists;
import com.meti.api.magma.collect.CollectionException;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.StreamException;
import com.meti.compile.token.*;

import java.util.List;
import java.util.Objects;

import static com.meti.api.magma.collect.Sequences.stream;
import static com.meti.compile.token.Attributes.EmptyFields;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public final class Implementation extends AbstractToken {
	public static final Neither Empty = new Neither(ArrayLists.empty());
	private final Field identity;
	private final Sequence<Field> parameters;
	private final Token body;

	public Implementation(Field identity, Sequence<Field> parameters, Token body) {
		this.identity = identity;
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Implementation;
			case Identity -> new FieldAttribute(identity);
			case Parameters -> createParameters();
			case Body -> new TokenAttribute(body);
			default -> throw new UnsupportedOperationException("Unknown query: " + query);
		};
	}

	private Attribute createParameters() {
		try {
			return stream(parameters)
					.fold(EmptyFields, Attribute.Builder::add)
					.complete();
		} catch (StreamException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		try {
			return switch (query) {
				case Identity -> copyIdentity(attribute);
				case Parameters -> copyParameters(attribute);
				case Body -> copyBody(attribute);
				default -> this;
			};
		} catch (StreamException e) {
			return this;
		}
	}

	private Token copyBody(Attribute attribute) throws StreamException {
		return stream(parameters)
				.fold(Empty, Neither::withParameter)
				.withIdentity(identity)
				.withBody(attribute.asToken())
				.complete();
	}

	private Token copyParameters(Attribute attribute) throws StreamException {
		return attribute.streamFields()
				.fold(Empty, Neither::withParameter)
				.withIdentity(identity)
				.withBody(body)
				.complete();
	}

	private Token copyIdentity(Attribute attribute) throws StreamException {
		return stream(parameters)
				.fold(Empty, Neither::withParameter)
				.withIdentity(attribute.asField())
				.withBody(body)
				.complete();
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

	public static record Neither(com.meti.api.magma.collect.List<Field> parameters) {
		public Implementation.WithIdentity withIdentity(Field identity) {
			return new Implementation.WithIdentity(identity, parameters);
		}

		public Neither withParameter(Field field) throws CollectionException {
			return new Neither(parameters.add(field));
		}
	}

	public record WithIdentity(Field identity, com.meti.api.magma.collect.List<Field> parameters) {
		public Implementation.Both withBody(Token body) {
			return new Implementation.Both(identity, parameters, body);
		}
	}

	public record Both(Field identity, com.meti.api.magma.collect.List<Field> parameters, Token body) {
		public Token complete() {
			return new Implementation(identity, parameters, body);
		}
	}
}
