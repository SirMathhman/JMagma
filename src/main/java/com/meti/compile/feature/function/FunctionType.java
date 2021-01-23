package com.meti.compile.feature.function;

import com.meti.api.magma.collect.ArrayLists;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.StreamException;
import com.meti.compile.token.*;

import java.util.Objects;

public final class FunctionType extends AbstractToken {
	private final Token returns;
	private final Sequence<Token> parameters;

	public FunctionType(Token returns, Sequence<Token> parameters) {
		this.returns = returns;
		this.parameters = parameters;
	}

	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Function;
			case Returns -> new TokenAttribute(returns);
			case Parameters -> new TokenSequenceAttribute(parameters);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return switch (query) {
			case Returns -> new FunctionType(attribute.asToken(), parameters);
			case Parameters -> Sequence<Token> result;Attribute attribute1 = attribute;
					try {
						result = attribute1.streamTokens().fold(ArrayLists.empty(), List::add);
					} catch (StreamException e) {
						result = ArrayLists.empty();
					}
					new FunctionType(returns, result);
			default -> this;
		};
	}

	@Override
	public Sequence<Query> list(Attribute.Type type) {
		return switch (type) {
			case Type -> ArrayLists.of(Query.Returns);
			case TypeList -> ArrayLists.of(Query.Parameters);
			default -> ArrayLists.empty();
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
