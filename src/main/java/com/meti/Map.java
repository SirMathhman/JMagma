package com.meti;

import com.meti.api.collect.List;

public interface Map<K, V> {
	Option<V> get(K key);

	List<K> orderedKeys();

	boolean containsKey(K key);
}
