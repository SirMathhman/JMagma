package com.meti.compile.feature.block;

import com.meti.api.core.EF1;
import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Block implements Node {
	private final List<? extends Node> children;

	private Block(List<? extends Node> children) {
		this.children = children;
	}

	public static Block Block(Node... children) {
		return Block(List.of(children));
	}

	public static Block Block(List<? extends Node> children) {
		return new Block(children);
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
		return Block(newChildren);
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
