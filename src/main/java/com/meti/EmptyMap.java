package com.meti;

public class EmptyMap<K, V> implements Map<K, V> {
	@Override
	public Option<V> get(K key) {
		return null;
	}

	@Override
	public List<K> orderedKeys() {
		return null;
	}
}
