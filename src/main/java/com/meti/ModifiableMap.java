package com.meti;

public interface ModifiableMap<K, V> extends Map<K, V> {
	ModifiableMap<K, V> putAll(Map<K, V> others);
}
