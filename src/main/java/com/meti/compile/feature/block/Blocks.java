package com.meti.compile.feature.block;

import com.meti.api.java.collect.JavaList;
import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.List;
import com.meti.compile.token.*;

import java.util.Collections;

public class Blocks {
	public static final Builder Empty = new Builder(JavaLists.empty());

	public Blocks() {
	}

	public static Block of(List<Token> lines) {
		return new Block(lines);
	}

	public static record Builder(List<Token> lines) {
		public Builder add(Token line) {
			return new Builder(lines.add(line));
		}

		public Token complete() {
			return new Block(lines);
		}
	}

	private static final class Block extends AbstractToken {
		private final List<Token> lines;

		Block(List<Token> lines) {
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
			return query == Query.Lines ? of(new JavaList<>(JavaLists.toJava(attribute.asTokenList()))) : this;
		}

		@Override
		public com.meti.api.magma.collect.List<Query> list(Attribute.Type type) {
			return new JavaList<>(type == Attribute.Type.NodeList ?
					Collections.singletonList(Query.Lines) :
					Collections.emptyList());
		}
	}
}
