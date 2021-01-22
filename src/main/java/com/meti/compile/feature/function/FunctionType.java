package com.meti.compile.feature.function;

import com.meti.api.java.collect.JavaLists;
import com.meti.compile.token.*;

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
		return switch (query) {
			case Group -> GroupAttribute.Function;
			case Returns -> new TokenAttribute(returns);
			case Parameters -> new TokenListAttribute(parameters);
			default -> throw new UnsupportedOperationException();
		};
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		return switch (query) {
			case Returns -> new FunctionType(attribute.asToken(), parameters);
			case Parameters -> List<Token> result;Attribute attribute1 = attribute;
					result = JavaLists.toJava(attribute1.asTokenList());
					new FunctionType(returns, result);
			default -> this;
		};
	}

	@Override
	public List<Query> list(Attribute.Type type) {
		return switch (type) {
			case Type -> List.of(Query.Returns);
			case TypeList -> List.of(Query.Parameters);
			default -> super.list(null);
		};
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
	public int hashCode() {
		return Objects.hash(returns, parameters);
	}

	@Override
	public String toString() {
		return "FunctionType[" +
		       "returns=" + returns + ", " +
		       "parameters=" + parameters + ']';
	}

}
