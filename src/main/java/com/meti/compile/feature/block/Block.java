package com.meti.compile.feature.block;

import com.meti.api.core.EF1;
import com.meti.compile.feature.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Block implements Node {
	private final List<Node> children;

	private Block(List<Node> children) {
		this.children = children;
	}

	public static Block Block(Node... children) {
		return Block(List.of(children));
	}

	public static Block Block(List<Node> children) {
		return new Block(children);
	}

	@Override
	public <E extends Exception> Node mapByChildren(EF1<Node, Node, E> mapper) throws E {
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
}
