package com.meti.api.collect.map;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.list.List;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.core.Comparable;
import com.meti.api.core.Option;
import com.meti.api.extern.Function2;

import static com.meti.api.collect.stream.SequenceStream.SequenceStream;
import static com.meti.api.core.None.None;

public class ListMap<K, V> implements Map<K, V> {
	private final List<Binding> bindings;
	private final Function2<K, K, Integer> comparator;

	private ListMap(Function2<K, K, Integer> comparator) {
		this(ArrayList.ofComparables(), comparator);
	}

	private ListMap(List<Binding> bindings, Function2<K, K, Integer> comparator) {
		this.bindings = bindings;
		this.comparator = comparator;
	}

	public static <K extends Comparable<K>, V> Map<K, V> ListMap(K key, V value) {
		return ListMap(Comparable::compareTo, key, value);
	}

	public static <K, V> Map<K, V> ListMap(Function2<K, K, Integer> comparator, K key, V value) {
		return new ListMap<K, V>(comparator).put(key, value);
	}


	public static <K, V> Map<K, V> ListMap(Function2<K, K, Integer> comparator) {
		return new ListMap<>(comparator);
	}

	@Override
	public int size() {
		return bindings.size();
	}

	@Override
	public boolean containsKey(K key) {
		try {
			return SequenceStream(bindings).anyMatch(binding -> binding.hasKey(key));
		} catch (StreamException e) {
			return false;
		}
	}

	@Override
	public Map<K, V> remove(K key) {
		try {
			return new ListMap<K, V>(SequenceStream(bindings)
					.filter(binding -> binding.hasKey(key))
					.head()
					.map(bindings::remove)
					.orElse(bindings), comparator);
		} catch (StreamException e) {
			return this;
		}
	}

	@Override
	public Map<K, V> put(K key, V value) {
		if (containsKey(key)) {
			return remove(key).put(key, value);
		} else {
			return new ListMap<K, V>(bindings.add(new Binding(key, value)), comparator);
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

	private class Binding implements Comparable<Binding> {
		private final K key;
		private final V value;

		Binding(K key, V value) {
			this.key = key;
			this.value = value;
		}

		boolean hasKey(K key) {
			return comparator.apply(this.key, key) == 0;
		}

		@Override
		public int compareTo(Binding other) {
			return comparator.apply(this.key, key);
		}
	}
}
