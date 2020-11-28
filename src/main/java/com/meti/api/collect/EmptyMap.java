package com.meti.api.collect;

import com.meti.List;
import com.meti.Map;
import com.meti.Option;

import static com.meti.api.collect.EmptyList.EmptyList;
import static com.meti.api.core.None.None;

public class EmptyMap<K, V> implements Map<K, V> {
	private EmptyMap() {
	}

	public static <K, V> EmptyMap<K, V> EmptyMap() {
		return new EmptyMap<K, V>();
	}

	@Override
	public Option<V> get(K key) {
		return None();
	}

	@Override
	public List<K> orderedKeys() {
		return EmptyList();
	}
}
