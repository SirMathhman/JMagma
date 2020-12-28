package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.*;

public final record MapResult<K, V extends Enum<V>>(Map<K, Cache<V>> map) implements Result<K, V> {

	@Override
	public Result<K, V> put(Node node, K header, V include) {
		var internalMap = map;
		if (!internalMap.containsKey(header)) {
			internalMap.put(header, new MapCache<V>(Collections.emptyMap()));
		}
		internalMap.get(header).put(include, node);
		return new MapResult<>(internalMap);
	}

	@Override
	public List<K> listClasses() {
		return new ArrayList<>(map.keySet());
	}

	@Override
	public String render(K clazz) {
		if (map.containsKey(clazz)) {
			var cache = map.get(clazz);
			return cache.render();
		} else {
			return "";
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (MapResult<?, ?>) obj;
		return Objects.equals(this.map, that.map);
	}

	@Override
	public int hashCode() {
		return Objects.hash(map);
	}

	@Override
	public String toString() {
		return "MapResult[" +
		       "map=" + map + ']';
	}

}
