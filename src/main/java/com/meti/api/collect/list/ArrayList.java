package com.meti.api.collect.list;

import com.meti.api.collect.Container;
import com.meti.api.collect.IndexException;
import com.meti.api.collect.Sequence;
import com.meti.api.collect.Set;
import com.meti.api.collect.stream.Stream;
import com.meti.api.core.Comparator;
import com.meti.api.core.Equator;
import com.meti.api.extern.Function1;
import com.meti.api.extern.Function2;

import static com.meti.api.collect.IndexException.IndexException;
import static com.meti.api.collect.stream.SequenceStream.SequenceStream;

public class ArrayList<T> implements List<T> {
	private static final int DefaultSize = 10;
	private final int size;
	private final Object[] array;
	private final Equator<T> comparator;

	private ArrayList(Object[] array, int internalSize, Equator<T> comparator) {
		this.size = internalSize;
		this.array = array;
		this.comparator = comparator;
	}

	public static <T> List<T> empty(Equator<T> comparator) {
		return new ArrayList<>(new Object[DefaultSize], 0, comparator);
	}

	@SafeVarargs
	public static <T> List<T> of(Equator<T> comparator, T... elements) {
		return new ArrayList<>(elements, elements.length, comparator);
	}

	public static <T> List<T> range(T startInclusive, T endExclusive, Comparator<T> comparator, Function1<T, T> next) throws IndexException {
		if (comparator.equalsTo(endExclusive, startInclusive)) return ArrayList.empty(comparator);
		if (comparator.compareTo(endExclusive, startInclusive) < 0) throw IndexException("End is less than start.");
		var current = startInclusive;
		var list = ArrayList.empty(comparator);
		while (!comparator.equalsTo(current, endExclusive)) {
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
		return size;
	}

	@Override
	public boolean contains(T t) {
		return first(element -> comparator.equalsTo(element, t)) != -1;
	}

	private int first(Function1<T, Boolean> predicate) {
		for (int i = 0; i < size; i++) {
			if (predicate.apply((T) array[i])) {
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
	public List<T> clear() {
		return empty(comparator);
	}

	@Override
	public List<T> allValues(Function1<T, Boolean> predicate) {
		return SequenceStream(this)
				.filter(predicate)
				.foldLeft(clear(), List::add);
	}

	@Override
	public boolean intersects(Container<T> other) {
		return false;
	}

	@Override
	public List<T> removeAll(Function1<T, Boolean> predicate) {
		return SequenceStream(allValues(predicate)).foldLeft(this, (Function2<List<T>, T, List<T>>) Set::removeFirst);
	}

	@Override
	public List<T> remove(int index) throws IndexException {
		if (index < 0) throw IndexException("Index cannot be negative.");
		if (index >= size) throw IndexException("Index cannot exceed or be equal to size.");
		for (int i = index; i < array.length - 1; i++) {
			array[i] = array[i + 1];
		}
		return new ArrayList<>(array, size - 1, comparator);
	}

	@Override
	public List<T> removeFirst(T t) {
		try {
			return remove(first(element -> comparator.equalsTo(element, t)));
		} catch (IndexException e) {
			return this;
		}
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
	public Stream<T> stream() {
		return SequenceStream(this);
	}

	@Override
	public boolean equalsTo(Sequence<T> other) {
		return false;
	}
}
