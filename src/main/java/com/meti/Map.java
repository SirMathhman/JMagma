package com.meti;

public interface Map<K, V> {
	Option<V> get(K key);

	List<K> orderedKeys();
}
