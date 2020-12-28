package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.*;

public final record MapResult<K, V extends Enum<V>>(Map<K, Cache<V>> map) implements Result<K> {

	MapResult<K, V> put(Node node, K header, V include) {
		var m = getMap();
		if(!m.containsKey(header)) {
			m.put(header, new MapCache<V>(Collections.emptyMap()));
		}
		m.get(header).put(include, node);
		return this;
	}

	@Override
	public List<K> listKeys() {
		return new ArrayList<>(map.keySet());
	}

	@Override
	public String renderToString(K type) {
		if (map.containsKey(type)) {
			var cache = map.get(type);
			return cache.render();
		} else {
			return "";
		}
	}

	public Map<K, Cache<V>> getMap() {
		return map;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (MapResult) obj;
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
