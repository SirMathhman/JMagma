package com.meti.compile.feature.block;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.EmptySequence;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.SingletonSequence;
import com.meti.compile.token.*;

import java.util.Objects;

public class Blocks {
	public static final Builder Empty = new Builder(JavaLists.empty());

	public Blocks() {
	}

	public static Token of(Sequence<Token> lines) {
		return new Impl(lines);
	}

	public static record Builder(List<Token> lines) {
		public Builder add(Token line) {
			return new Builder(lines.add(line));
		}

		public Token complete() {
			return new Impl(lines);
		}
	}

	private static final class Impl extends AbstractToken {
		private final Sequence<Token> lines;

		Impl(Sequence<Token> lines) {
			this.lines = lines;
		}

		@Override
		public Attribute apply(Query query) {
			return switch (query) {
				case Group -> GroupAttribute.Block;
				case Lines -> new TokenListAttribute(JavaLists.toJava(lines));
				default -> throw new UnsupportedOperationException();
			};
		}

		@Override
		public Token copy(Query query, Attribute attribute) {
			return query == Query.Lines ? of(attribute.asTokenList()) : this;
		}

		@Override
		public Sequence<Query> list(Attribute.Type type) {
			return type == Attribute.Type.NodeList ?
					new SingletonSequence<>(Query.Lines) :
					new EmptySequence<>();
		}

		@Override
		public int hashCode() {
			return Objects.hash(lines);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Impl impl = (Impl) o;
			return Objects.equals(lines, impl.lines);
		}
	}
}
