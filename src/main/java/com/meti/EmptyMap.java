package com.meti;

import static com.meti.None.None;

public class EmptyMap<K, V> implements Map<K, V> {
	@Override
	public Option<V> get(K key) {
		return None();
	}

	@Override
	public List<K> orderedKeys() {
		return EmptyList.EmptyList();
	}
}
