package com.meti.compile.feature.function;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequence;
import com.meti.compile.token.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class FunctionType extends AbstractToken {
	private final Token returns;
	private final List<Token> parameters;

	public FunctionType(Token returns, List<Token> parameters) {
		this.returns = returns;
		this.parameters = parameters;
	}

	@Override
	public Attribute apply(Query query) {
		switch (query) {
			case Group:
				return GroupAttribute.Function;
			case Returns:
				return new TokenAttribute(returns);
			case Parameters:
				return new TokenSequenceAttribute(JavaLists.fromJava(parameters));
			default:
				throw new UnsupportedOperationException();
		}
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return switch (query) {
			case Returns -> new FunctionType(attribute.asToken(), parameters);
			case Parameters -> new FunctionType(returns, JavaLists.toJava(attribute.asTokenSequence()));
			default -> this;
		};
	}

	@Override
	public Sequence<Query> list(Attribute.Type type) {
		return JavaLists.fromJava(list1(type));
	}

	private List<Query> list1(Attribute.Type type) {
		return switch (type) {
			case Type -> List.of(Query.Returns);
			case TypeList -> List.of(Query.Parameters);
			default -> Collections.emptyList();
		};
	}

	@Override
	public int hashCode() {
		return Objects.hash(returns, parameters);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (FunctionType) obj;
		return Objects.equals(this.returns, that.returns) &&
		       Objects.equals(this.parameters, that.parameters);
	}

	@Override
	public String toString() {
		return "FunctionType[" +
		       "returns=" + returns + ", " +
		       "parameters=" + parameters + ']';
	}

}
