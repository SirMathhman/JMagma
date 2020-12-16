package com.meti.api.collect.map;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.list.List;
import com.meti.api.collect.stream.Stream;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.core.Equatable;
import com.meti.api.core.Equator;
import com.meti.api.core.Option;
import com.meti.api.extern.Function0;

import static com.meti.api.collect.stream.SequenceStream.SequenceStream;
import static com.meti.api.core.None.None;

public class ListMap<K, V> implements OrderedMap<K, V> {
	private final List<Binding<K, V>> bindings;
	private final Equator<K> equator;

	private ListMap(Equator<K> equator) {
		this(ArrayList.empty(Binding::equalsTo), equator);
	}

	private ListMap(List<Binding<K, V>> bindings, Equator<K> equator) {
		this.bindings = bindings;
		this.equator = equator;
	}

	public static <K extends Equatable<K>, V> Map<K, V> ListMap(K key, V value) {
		return ListMap(Equatable::equalsTo, key, value);
	}

	public static <K, V> Map<K, V> ListMap(Equator<K> equator, K key, V value) {
		return new ListMap<K, V>(equator).put(key, value);
	}


	public static <K, V> Map<K, V> ListMap(Equator<K> equator) {
		return new ListMap<>(equator);
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
			return new ListMap<>(SequenceStream(bindings)
					.filter(binding -> binding.hasKey(key))
					.head()
					.map(bindings::removeFirst)
					.orElse(bindings), equator);
		} catch (StreamException e) {
			return this;
		}
	}

	@Override
	public Map<K, V> put(K key, V value) {
		if (containsKey(key)) {
			return remove(key).put(key, value);
		} else {
			return new ListMap<>(bindings.add(new Binding<>(equator, key, value)), equator);
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

	@Override
	public Map<K, V> ensure(K key, Function0<V> value) {
		if (containsKey(key)) {
			return this;
		} else {
			return put(key, value.get());
		}
	}

	@Override
	public Stream<K> streamKeys() {
		return bindings.stream().map(Binding::asKey);
	}

	private static class Binding<K, V> implements Equatable<Binding<K, V>> {
		private final K key;
		private final V value;
		private final Equator<K> equator;

		Binding(Equator<K> equator, K key, V value) {
			this.key = key;
			this.value = value;
			this.equator = equator;
		}

		K asKey() {
			return key;
		}

		boolean hasKey(K key) {
			return equator.equalsTo(this.key, key);
		}

		@Override
		public boolean equalsTo(Binding<K, V> other) {
			return equator.equalsTo(this.key, other.key);
		}
	}
}
