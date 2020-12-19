package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cache<T extends Enum<T>> {
	private final Map<T, List<Node>> map;

	public Cache(Map<T, List<Node>> map) {
		this.map = map;
	}

	String render() {
		return map.keySet()
				.stream()
				.sorted((o1, o2) -> -o1.compareTo(o2))
				.map(map::get)
				.flatMap(List::stream)
				.map(Node::render)
				.collect(Collectors.joining());
	}

	Cache<T> put(T type, Node node) {
		Map<T, List<Node>> copy = new HashMap<>(this.map);
		if (!copy.containsKey(type)) {
			copy.put(type, new ArrayList<>());
		}
		copy.get(type).add(node);
		return new Cache<>(copy);
	}
}
