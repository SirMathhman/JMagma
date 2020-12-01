package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;
import com.meti.api.extern.Function1;

import java.util.Objects;

public class ArrayList<T> implements MutableList<T> {
	private static final int GrowthFactor = 2;
	private static final int DEFAULT_CAPACITY = 10;
	private final Object[] internalArray;
	private final int capacity;
	private final int size;

	private ArrayList(Object[] internalArray, int capacity, int size) {
		this.internalArray = internalArray;
		this.capacity = capacity;
		this.size = size;
	}

	public static MutableList<Integer> Range(int from, int to) {
		return ArrayList(from, i -> i < to, i -> i + 1);
	}

	public static <T> MutableList<T> ArrayList(T identity, Function1<T, Boolean> condition, Function1<T, T> incrementor) {
		var list = ArrayList.<T>ArrayList();
		for (T i = identity; condition.apply(i); i = incrementor.apply(i)) {
			list.add(i);
		}
		return list;
	}

	public static <T> MutableList<T> ArrayList() {
		return ArrayList(DEFAULT_CAPACITY);
	}

	public static <T> MutableList<T> ArrayList(int capacity) {
		return ArrayList(new Object[capacity], capacity, 0);
	}

	@SafeVarargs
	public static <T> MutableList<T> ArrayList(T... array) {
		return new ArrayList<>(array, array.length, array.length);
	}

	public static <T> MutableList<T> ArrayList(Object[] internalArray, int capacity, int size) {
		return new ArrayList<>(internalArray, capacity, size);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ArrayList<?> arrayList = (ArrayList<?>) o;
		if (size == arrayList.size) {
			for (int i = 0; i < size; i++) {
				if (!internalArray[i].equals(arrayList.internalArray[i])) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(size);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T get(int index) throws IndexException {
		return bounded(index, index1 -> (T) internalArray[index1]);
	}

	private <R> R bounded(int index, Function1<Integer, R> applicator) throws IndexException {
		if (index < 0) throw IndexException.IndexException("Index %d cannot be negative.".formatted(index));
		if (index >= size) {
			String format = "Index %d cannot be greater or equal to the number of elements available.";
			String message = format.formatted(index);
			throw IndexException.IndexException(message);
		}
		return applicator.apply(index);
	}

	private Object[] resizeTo(int desiredCapacity) {
		if (capacity < desiredCapacity) {
			int newCapacity = capacity;
			while (newCapacity < desiredCapacity) {
				newCapacity = newCapacity * GrowthFactor;
			}
			Object[] copy = new Object[newCapacity];
			System.arraycopy(internalArray, 0, copy, 0, capacity);
			return copy;
		} else {
			return internalArray;
		}
	}

	@Override
	public MutableList<T> set(int index, T value) throws IndexException {
		if (index < 0) {
			String format = "Index %d can't be negative.";
			String message = format.formatted(index);
			throw IndexException.IndexException(message);
		}
		Object[] copy = resizeTo(index + 1);
		copy[index] = value;
		int newSize = Math.max(index + 1, size);
		return ArrayList(copy, copy.length, newSize);
	}

	@Override
	public MutableList<T> add(T t) {
		try {
			return set(size, t);
		} catch (IndexException e) {
			return this;
		}
	}

	@Override
	public MutableList<T> addAll(List<T> others) {
		int size = others.size();
		MutableList<T> current = this;
		for (int i = 0; i < size; i++) {
			try {
				current = current.add(others.get(i));
			} catch (IndexException ignored) {
			}
		}
		return current;
	}

	@Override
	public <R> MutableList<R> empty() {
		return ArrayList();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
}
