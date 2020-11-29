package com.meti.api.collect;

import com.meti.*;
import com.meti.api.extern.ExceptionalFunction1;
import com.meti.api.extern.Function0;

import static com.meti.ListStream.ListStream;
import static com.meti.api.collect.ArrayList.ArrayList;
import static com.meti.api.collect.EmptyList.EmptyList;
import static com.meti.api.core.None.None;

public class ListMap<K, V> implements MutableMap<K, V> {
	private final MutableList<Binding<K, V>> bindings;

	private ListMap() {
		this(ArrayList());
	}

	public ListMap(MutableList<Binding<K, V>> bindings) {
		this.bindings = bindings;
	}

	public static <K, V> MutableMap<K, V> ListMap() {
		return new ListMap<>();
	}

	static <K, V> Binding<K, V> Binding(K key, V value) {
		return new Binding<>(key, value);
	}

	@Override
	public boolean containsKey(K key) {
		return ListStream(bindings).anyMatch(binding -> binding.hasKey(key));
	}

	@Override
	public Option<V> get(K key) {
		try {
			return ListStream(bindings)
					.filter(binding -> binding.hasKey(key))
					.mapExceptionally(Binding::toValue)
					.head();
		} catch (StreamException e) {
			return None();
		}
	}

	@Override
	public ListMap<K, V> put(K key, V value) {
		return new ListMap<>(bindings.add(Binding(key, value)));
	}

	@Override
	public ListMap<K, V> ensure(K key, V value) {
		return containsKey(key) ? this : put(key, value);
	}

	@Override
	public MutableMap<K, V> putAll(Map<K, V> others) {
		List<K> keys = others.orderedKeys();
		ExceptionalFunction1<K, V, Exception> otherSupplier = key -> {
			Function0<StateException> supplier = () -> {
				String format = "Neither this map or the supplied map had '%s' as a key.";
				String message = format.formatted(key);
				return new StateException(message);
			};
			return others.get(key).orElseThrow(supplier);
		};
		try {
			return ListStream(ArrayList.<K>ArrayList().addAll(keys))
					.foldExceptionally(this, (current, k) -> current.ensure(k, otherSupplier.apply(k)));
		} catch (StreamException e) {
			return this;
		}

		/*
		const keys = others.orderedKeys();
		const otherSupplier = key -> others(key).orElseThrow(() -> {
			const format = "Neither this mapExceptionally or the supplied mapExceptionally had '%s' as a key.";
			const message = format.formatted(key);
			return new StateException(message);
		});
		return try ListStream<>(keys).foldExceptionally(this, _.ensure(_, otherSupplier));
			   catch case (e : Exception) => this;
		*/
	}

	@Override
	public List<K> orderedKeys() {
		try {
			return ListStream(bindings)
					.map(Binding::toKey)
					.fold(ArrayList(), MutableList::add);
		} catch (StreamException e) {
			return EmptyList();
		}
	}

	static class Binding<K, V> {
		private final K key;
		private final V value;

		private Binding(K key, V value) {
			this.key = key;
			this.value = value;
		}

		boolean hasKey(K otherKey) {
			return key.equals(otherKey);
		}

		public V toValue() {
			return value;
		}

		public K toKey() {
			return key;
		}
	}
}
