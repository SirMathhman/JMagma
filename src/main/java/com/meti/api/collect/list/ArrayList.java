package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.Sequence;
import com.meti.api.core.Comparable;
import com.meti.api.core.Primitives;
import com.meti.api.extern.Function1;
import com.meti.api.extern.Function2;

import static com.meti.api.collect.IndexException.IndexException;

public class ArrayList<T> implements List<T> {
	private static final int DefaultSize = 10;
	private final int size;
	private final Object[] array;
	private final Function2<T, T, Integer> comparator;

	private ArrayList(Object[] array, int internalSize, Function2<T, T, Integer> comparator) {
		this.size = internalSize;
		this.array = array;
		this.comparator = comparator;
	}

	public static <T> List<T> empty(Function2<T, T, Integer> comparator) {
		return new ArrayList<>(new Object[DefaultSize], 0, comparator);
	}

	@SafeVarargs
	public static <T> List<T> of(Function2<T, T, Integer> comparator, T... elements) {
		return new ArrayList<>(elements, elements.length, comparator);
	}

	@SafeVarargs
	public static <T extends Comparable<T>> List<T> ofComparables(T... elements) {
		Object[] array1 = new Object[DefaultSize];
		return elements.length != 0 ?
				new ArrayList<>(elements, elements.length, Comparable::compareTo) :
				new ArrayList<>(array1, 0, Comparable::compareTo);
	}

	public static <T> List<T> range(T startInclusive, T endExclusive, Function2<T, T, Integer> comparator, Function1<T, T> next) {
		var current = startInclusive;
		var list = ArrayList.empty(comparator);
		while (comparator.apply(current, endExclusive) != 0) {
			list = list.add(current);
			current = next.apply(current);
		}
		return list;
	}

	@Override
	public T apply(int index) throws IndexException {
		if (index < 0) throw IndexException("Index can't be negative");
		if (index >= size) throw IndexException("Index is equal to or exceeds the size of this list.");
		return (T) array[index];
	}

	@Override
	public int size() {
		return size
				;
	}

	@Override
	public boolean contains(T t) {
		return indexOf(t) != -1;
	}

	@Override
	public int indexOf(T t) {
		for (int i = 0; i < size; i++) {
			if (comparator.apply((T) array[i], t) == 0) {
				return i;
			}
		}
		return -1;
	}

	private Object[] resizeTo(int index) {
		var capacity = array.length;
		if (index < array.length) return array;
		if (capacity == 0) capacity = 1;
		while (capacity < index + 1) {
			capacity = capacity * 2;
		}
		var copy = new Object[capacity];
		if (this.size >= 0) System.arraycopy(array, 0, copy, 0, this.size);
		return copy;
	}

	@Override
	public List<T> set(int index, T t) {
		var newArray = resizeTo(index);
		newArray[index] = t;
		return new ArrayList<>(newArray, Math.max(index + 1, size), comparator);
	}

	@Override
	public List<T> remove(T t) {
		var index = indexOf(t);
		if (index != -1) {
			for (int i = index; i < array.length - 1; i++) {
				array[i] = array[i + 1];
			}
			return new ArrayList<>(array, size - 1, comparator);
		}
		return this;
	}

	@Override
	public Object[] asArray() {
		var copy = new Object[size];
		if (size >= 0) System.arraycopy(array, 0, copy, 0, size);
		return copy;
	}

	@Override
	public List<T> add(T t) {
		return set(size, t);
	}

	@Override
	public int compareTo(Sequence<T> o) {
		var compSize = Primitives.comparingInts(size, o.size());
		if (compSize == 0) {
			for (int i = 0; i < size; i++) {
				try {
					var thisElement = apply(i);
					var otherElement = o.apply(i);
					var compElement = comparator.apply(thisElement, otherElement);
					if (compElement != 0) return compElement;
				} catch (IndexException ignored) {
				}
			}
			return 0;
		} else {
			return compSize;
		}
	}
}
