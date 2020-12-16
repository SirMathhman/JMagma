package com.meti.api.collect.map;

import com.meti.api.collect.stream.Stream;

public interface OrderedMap<K, V> extends Map<K, V> {
	Stream<K> streamKeys();
}
