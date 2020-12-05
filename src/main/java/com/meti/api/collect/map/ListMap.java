package com.meti.api.collect.map;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.list.List;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.core.Comparable;
import com.meti.api.core.Option;

import static com.meti.api.collect.stream.SequenceStream.SequenceStream;
import static com.meti.api.core.None.None;

public class ListMap<K extends Comparable<K>, V> implements Map<K, V> {
	private final List<Binding<K, V>> bindings;

	public ListMap() {
		this(ArrayList.ArrayList());
	}

	private ListMap(List<Binding<K, V>> bindings) {
		this.bindings = bindings;
	}

	public boolean containsKey(K key) {
		try {
			return SequenceStream(bindings).anyMatch(binding -> binding.hasKey(key));
		} catch (StreamException e) {
			return false;
		}
	}

	public Map<K, V> remove(K key) {
		try {
			return new ListMap<>(SequenceStream(bindings)
					.filter(binding -> binding.hasKey(key))
					.head()
					.map(bindings::remove)
					.orElse(bindings));
		} catch (StreamException e) {
			return this;
		}
	}

	@Override
	public Map<K, V> put(K key, V value) {
		if (containsKey(key)) {
			return remove(key).put(key, value);
		} else {
			return new ListMap<>(bindings.add(new Binding<>(key, value)));
		}
	}

	@Override
	public Option<V> get(K key) {
		try {
			return SequenceStream(bindings)
					.filter(binding -> binding.hasKey(key))
					.map(binding -> binding.value)
					.head();
		} catch (StreamException e) {
			return None();
		}
	}

	static class Binding<K extends Comparable<K>, V> implements Comparable<Binding<K, V>> {
		private final K key;
		private final V value;

		Binding(K key, V value) {
			this.key = key;
			this.value = value;
		}

		boolean hasKey(K key) {
			return this.key.compareTo(key) == 0;
		}

		@Override
		public int compareTo(Binding<K, V> other) {
			return this.key.compareTo(other.key);
		}
	}
}
