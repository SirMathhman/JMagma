package com.meti;

import com.meti.api.collect.ListMap;
import com.meti.api.extern.ExceptionalFunction1;

public interface MutableMap<K, V> extends Map<K, V> {
	ListMap<K, V> put(K key, V value);

	ListMap<K, V> ensure(K key, V value);

	MutableMap<K, V> putAll(Map<K, V> others);
}
