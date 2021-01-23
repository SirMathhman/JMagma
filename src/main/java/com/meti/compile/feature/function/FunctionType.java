package com.meti.compile.feature.function;

import com.meti.api.magma.collect.*;
import com.meti.compile.token.*;

import java.util.Objects;

import static com.meti.api.magma.collect.Sequences.stream;

public class FunctionType {
	public static final WithoutReturn Empty = new WithoutReturn(ArrayLists.empty());

	public FunctionType() {
	}

	private static final class Impl extends AbstractToken {
		private final Token returns;
		private final Sequence<Token> parameters;

		private Impl(Token returns, Sequence<Token> parameters) {
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
			try {
				return switch (query) {
					case Returns -> stream(parameters)
							.fold(Empty, WithoutReturn::withParameter)
							.withReturn(attribute.asToken())
							.complete();
					case Parameters -> attribute.streamTokens()
							.fold(Empty, WithoutReturn::withParameter)
							.withReturn(attribute.asToken())
							.complete();
					default -> this;
				};
			} catch (StreamException e) {
				return this;
			}
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
			var that = (Impl) obj;
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

	public static record WithoutReturn(List<Token> parameters) {
		public WithoutReturn withParameter(Token token) throws CollectionException {
			return new WithoutReturn(parameters.add(token));
		}

		public WithReturn withReturn(Token returns) {
			return new WithReturn(returns, parameters);
		}
	}

	public static record WithReturn(Token returns, List<Token> parameters) {
		public Token complete() {
			return new Impl(returns, parameters);
		}
	}
}
