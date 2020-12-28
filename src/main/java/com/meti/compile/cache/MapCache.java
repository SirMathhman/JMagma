package com.meti.compile.cache;

import com.meti.compile.token.Node;

import java.util.*;
import java.util.stream.Collectors;

public record MapCache<T extends Enum<T>>(Map<T, List<Node>> map) implements Cache<T> {
	@Override
	public String render() {
		return map.keySet()
				.stream()
				.sorted(Comparator.naturalOrder())
				.map(map::get)
				.flatMap(List::stream)
				.map(Node::render)
				.collect(Collectors.joining());
	}

	@Override
	public Cache<T> put(T type, Node node) {
		Map<T, List<Node>> copy = new HashMap<>(this.map);
		if (!copy.containsKey(type)) {
			copy.put(type, new ArrayList<>());
		}
		copy.get(type).add(node);
		return new MapCache<>(copy);
	}
}
