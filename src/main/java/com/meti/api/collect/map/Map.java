package com.meti.api.collect.map;

import com.meti.api.core.Option;
import com.meti.api.extern.Function0;

public interface Map<K, V> {
	int size();

	boolean containsKey(K key);

	Map<K, V> remove(K key);

	Map<K, V> put(K key, V value);

	Option<V> get(K key);

	Map<K, V> ensure(K key, Function0<V> value);
}
