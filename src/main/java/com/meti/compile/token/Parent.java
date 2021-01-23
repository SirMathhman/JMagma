package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.ArrayLists;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.Sequences;
import com.meti.api.magma.collect.StreamException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Parent extends AbstractToken {
	private final Sequence<Token> lines;

	public Parent(Sequence<Token> lines) {
		this.lines = lines;
	}

	@Override
	public Attribute apply(Query query) {
		return switch (query) {
			case Group -> GroupAttribute.Parent;
			case Lines -> new TokenSequenceAttribute(lines);
			case Value -> new StringAttribute(computeValue());
			default -> throw new UnsupportedOperationException();
		};
	}

	private String computeValue() {
		try {
			return Sequences.stream(lines)
					.filter(this::validateLine)
					.map(line -> line.apply(Query.Value))
					.map(Attribute::asString)
					.fold(String::concat);
		} catch (StreamException e) {
			return "";
		}
	}

	private boolean validateLine(Token line) {
		var isContent = Tokens.is(line, GroupAttribute.Content);
		var isParent = Tokens.is(line, GroupAttribute.Parent);
		return isContent || isParent;
	}

	@Override
	public Token copy(Query query, Attribute attribute) {
		Sequence<Token> result;
		try {
			result = attribute.streamTokens().fold(ArrayLists.empty(), com.meti.api.magma.collect.List::add);
		} catch (StreamException e) {
			result = ArrayLists.empty();
		}
		return query == Query.Lines ? new Parent(result) : this;
	}

	@Override
	public Sequence<Query> list(Attribute.Type type) {
		return JavaLists.fromJava(list1(type));
	}

	@Override
	public int hashCode() {
		return Objects.hash(lines);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Parent parent = (Parent) o;
		return Objects.equals(lines, parent.lines);
	}

	private List<Query> list1(Attribute.Type type) {
		return type == Attribute.Type.NodeList ?
				Collections.singletonList(Query.Lines) :
				Collections.emptyList();
	}
}
