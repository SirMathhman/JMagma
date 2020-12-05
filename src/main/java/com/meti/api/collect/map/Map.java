package com.meti.api.collect.map;

import com.meti.api.core.Option;

public interface Map<K, V> {
	Map<K, V> put(K key, V value);

	Option<V> get(K key);
}
