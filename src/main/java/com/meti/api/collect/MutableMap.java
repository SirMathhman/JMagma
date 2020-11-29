package com.meti.api.collect;

public interface MutableMap<K, V> extends Map<K, V> {
	ListMap<K, V> put(K key, V value);

	ListMap<K, V> ensure(K key, V value);

	MutableMap<K, V> putAll(Map<K, V> others);
}
