package com.meti.api.collect;

import com.meti.Map;
import com.meti.Option;

import static com.meti.Some.Some;
import static com.meti.api.collect.SingletonList.SingletonList;
import static com.meti.api.core.None.None;

public class SingletonMap<K, V> implements Map<K, V> {
	private final K key;
	private final V value;

	public SingletonMap(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Option<V> get(K key) {
		return this.key.equals(key) ? Some(value) : None();
	}

	@Override
	public List<K> orderedKeys() {
		return SingletonList(key);
	}
}
