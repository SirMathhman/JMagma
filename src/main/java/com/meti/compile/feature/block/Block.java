package com.meti.compile.feature.block;

import com.meti.api.core.*;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public record Block(List<? extends Node> children) implements Node {
	public static Block Block(Node... children) {
		return new Block(List.of(children));
	}

	@Override
	public boolean is(Group group) {
		return group == Group.Block;
	}

	@Override
	public String toString() {
		return "Block{" +
		       "children=" + children +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Block block = (Block) o;
		return Objects.equals(children, block.children);
	}

	@Override
	public int hashCode() {
		return Objects.hash(children);
	}

	@Override
	public <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		var newChildren = new ArrayList<Node>();
		for (Node child : children) {
			newChildren.add(mapper.apply(child));
		}
		return new Block(newChildren);
	}

	@Override
	public <T> List<T> applyToChildren(F1<Node, T> mapper) {
		return children.stream()
				.map(mapper::apply)
				.collect(Collectors.toList());
	}

	@Override
	public <T, E extends Exception> List<T> applyToChildrenExceptionally(EF1<Node, T, E> mapper) throws E {
		var list = new ArrayList<T>();
		for (Node child : children) {
			list.add(mapper.apply(child));
		}
		return list;
	}

	@Override
	public Node mapByChildren(F1<Node, Node> mapper) {
		var newChildren = children.stream()
				.map(mapper::apply)
				.collect(Collectors.toList());
		return new Block(newChildren);
	}

	@Override
	public String render() {
		return children.stream()
				.map(Node::render)
				.collect(Collectors.joining("", "{", "}"));
	}

	@Override
	public Option<Field> findIdentity() {
		return findIdentity2()
				.map(Some::Some)
				.orElseGet(None::None);
	}

	@Deprecated
	private Optional<Field> findIdentity2() {
		return Optional.empty();
	}
}
