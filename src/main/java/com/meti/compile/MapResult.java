package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.*;

public final record MapResult<C, G extends Enum<G>>(Map<C, Cache<G>> map) implements Result<C, G> {

	@Override
	public Result<C, G> put(C clazz, G group, Node node) {
		var internalMap = map;
		if (!internalMap.containsKey(clazz)) {
			Map<G, List<Node>> vListMap = Collections.emptyMap();
			var value = new MapCache<G>(vListMap);
			internalMap.put(clazz, value);
		}
		var oldCache = internalMap.get(clazz);
		var newCache = oldCache.put(group, node);
		internalMap.put(clazz, newCache);
		return new MapResult<>(internalMap);
	}

	@Override
	public List<C> list() {
		return new ArrayList<>(map.keySet());
	}

	@Override
	public String render(C clazz) {
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
