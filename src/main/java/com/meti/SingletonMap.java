package com.meti;

import com.meti.api.collect.List;

public class SingletonMap<K, V> implements Map<K, V> {
	private final K key;
	private final V value;

	private SingletonMap(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public static <K, V> SingletonMap<K, V> SingletonMap(K key, V value) {
		return new SingletonMap<>(key, value);
	}

	@Override
	public Option<V> get(K key) {
		return null;
	}

	@Override
	public List<K> orderedKeys() {
		return null;
	}
}
