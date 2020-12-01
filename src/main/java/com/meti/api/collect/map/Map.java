package com.meti.api.collect.map;

import com.meti.api.collect.list.List;
import com.meti.api.core.Option;

public interface Map<K, V> {
	Option<V> get(K key);

	List<K> orderedKeys();

	boolean containsKey(K key);
}
