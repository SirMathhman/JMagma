package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;
import com.meti.api.core.Comparable;
import com.meti.api.extern.Function2;

public class ArrayList<T> implements List<T> {
	private static final int DefaultSize = 10;
	private final int size;
	private final Object[] array;
	private final Function2<T, T, Integer> comparator;

	private ArrayList(Object[] array, int size, Function2<T, T, Integer> comparator) {
		this.size = size;
		this.array = array;
		this.comparator = comparator;
	}

	@SafeVarargs
	public static <T> List<T> of(Function2<T, T, Integer> comparator, T... elements) {
		return elements.length != 0 ?
				ArrayList(elements, elements.length, comparator) :
				ArrayList(new Object[DefaultSize], 0, comparator);
	}

	@SafeVarargs
	public static <T extends Comparable<T>> List<T> ofComparables(T... elements) {
		return elements.length != 0 ?
				ArrayList(elements, elements.length) :
				ArrayList(new Object[DefaultSize], 0);
	}

	private static <T extends Comparable<T>> List<T> ArrayList(Object[] array, int size) {
		return ArrayList(array, size, Comparable::compareTo);
	}

	private static <T> List<T> ArrayList(Object[] array, int size, Function2<T, T, Integer> comparator) {
		return new ArrayList<>(array, size, comparator);
	}

	@Override
	public T apply(int index) throws IndexException {
		if (index < 0) throw IndexException.IndexException("Index can't be negative");
		if (index >= size) throw IndexException.IndexException("Index is equal to or exceeds the size of this list.");
		return (T) array[index];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T t) {
		return indexOf(t) != -1;
	}

	@Override
	public int indexOf(T t) {
		for (int i = 0; i < size; i++) {
			if ((double) comparator.apply((T) array[i], t) == 0) {
				return i;
			}
		}
		return -1;
	}

	private Object[] resizeTo(int index) {
		var sizeToAllocate = array.length;
		if (index < array.length) return array;
		if (sizeToAllocate == 0) sizeToAllocate = 1;
		while (sizeToAllocate < index + 1) {
			sizeToAllocate = sizeToAllocate * 2;
		}
		var copy = new Object[sizeToAllocate];
		if (size >= 0) System.arraycopy(array, 0, copy, 0, size);
		return copy;
	}

	@Override
	public List<T> set(int index, T t) {
		var newArray = resizeTo(index);
		newArray[index] = t;
		return ArrayList(newArray, Math.max(index + 1, size), comparator);
	}

	@Override
	public List<T> remove(T t) {
		var index = indexOf(t);
		if (index != -1) {
			/*
			Normally, when this code is translated to Magma,
			the default value will be placed here, i.e. Point.type.default,
			but because there's not really a way to do that in Java WELL,
			in addition to that Java doesn't instantly crash when the "null"
			keyword is used, then we're going to use that here. But note
			that this line won't actually exist in the Magma implementation.
			 */
			for (int i = index; i < array.length - 1; i++) {
				array[i] = array[i + 1];
			}
			return ArrayList(array, size - 1, comparator);
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
}
