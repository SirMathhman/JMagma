package com.meti;

import static com.meti.ArrayList.ArrayList;
import static com.meti.None.None;

public class ListMap<K, V> implements ModifiableMap<K, V> {
	private final List<Binding<K, V>> bindings;

	public ListMap() {
		this(ArrayList());
	}

	public ListMap(List<Binding<K, V>> bindings) {
		this.bindings = bindings;
	}

	public boolean containsKey(K key) {
		return new ListStream<>(bindings).anyMatch(binding -> binding.hasKey(key));
	}

	@Override
	public Option<V> get(K key) {
		try {
			return new ListStream<>(bindings)
					.filter(binding -> binding.hasKey(key))
					.map(Binding::toValue)
					.head();
		} catch (StreamException e) {
			return None();
		}
	}

	public ListMap<K, V> put(K key, V value) {
		return new ListMap<>(bindings.add(new Binding<>(key, value)));
	}

	public ListMap<K, V> ensure(K key, V value) {
		return containsKey(key) ? this : put(key, value);
	}

	public <E extends Exception> ListMap<K, V> ensure(K key, ExceptionalFunction1<K, V, E> mapper) throws E {
		return ensure(key, mapper.apply(key));
	}

	@Override
	public ModifiableMap<K, V> putAll(Map<K, V> others) {
		List<K> keys = others.orderedKeys();
		ExceptionalFunction1<K, V, Exception> otherSupplier = key -> {
			Function0<StateException> supplier = () -> {
				String format = "Neither this mapExceptionally or the supplied mapExceptionally had '%s' as a key.";
				String message = format.formatted(key);
				return new StateException(message);
			};
			return others.get(key).orElseThrow(supplier);
		};
		try {
			return new ListStream<>(keys).fold(this, (current, k) -> current.ensure(k, otherSupplier));
		} catch (StreamException e) {
			return this;
		}
	}

	@Override
	public List<K> orderedKeys() {
		return null;
	}

	static class Binding<K, V> {
		private final K key;
		private final V value;

		Binding(K key, V value) {
			this.key = key;
			this.value = value;
		}

		boolean hasKey(K otherKey) {
			return key.equals(otherKey);
		}

		public V toValue() {
			return value;
		}
	}
}
