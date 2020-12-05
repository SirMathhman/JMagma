package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;
import com.meti.api.core.Comparable;

import static com.meti.api.collect.IndexException.IndexException;

public class ArrayList<T extends Comparable<T>> implements List<T> {
	public static final int DEFAULT_SIZE = 10;
	private final Comparable<T>[] array;
	private final int size;

	private ArrayList(Comparable<T>[] array, int size) {
		this.array = array;
		this.size = size;
	}

	public static <T extends Comparable<T>> ArrayList<T> ArrayList() {
		return ArrayList(new Comparable[DEFAULT_SIZE], 0);
	}

	public static <T extends Comparable<T>> ArrayList<T> ArrayList(Comparable<T>[] array, int size) {
		return new ArrayList<>(array, size);
	}

	@Override
	public T apply(int index) throws IndexException {
		if (index < 0) throw IndexException("Index can't be negative");
		if (index >= array.length) throw IndexException("Index is equal to or exceeds the size of this list.");
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
			if (array[i].compareTo(t) == 0) {
				return i;
			}
		}
		return -1;
	}

	private Comparable<T>[] resizeTo(int index) {
		var sizeToAllocate = array.length;
		if (sizeToAllocate == 0) {
			sizeToAllocate = 1;
		} else {
			while (sizeToAllocate < index) {
				sizeToAllocate = sizeToAllocate * 2;
			}
		}
		var copy = new Comparable[sizeToAllocate];
		if (size >= 0) System.arraycopy(array, 0, copy, 0, size);
		return copy;
	}

	@Override
	public List<T> set(int index, T t) {
		if (index < size) {
			array[index] = t;
			return this;
		} else {
			var newArray = resizeTo(index);
			return ArrayList(newArray, index);
		}
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
			array[index] = null;
		}
		return this;
	}

	@Override
	public List<T> add(T t) {
		return set(size + 1, t);
	}
}
