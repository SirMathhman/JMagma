package com.meti.api.collect;

import com.meti.api.memory.Allocator;

import static com.meti.api.memory.DefaultAllocator.DefaultAllocator_;
import static java.lang.Math.round;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_SIZE = 10;
	private static final float GROWTH_FACTOR = 1.5f;
	private final Object[] elements;
	private final int size;
	private final Allocator allocator;

	public ArrayList(Object[] elements, int size, Allocator allocator) {
		this.elements = elements;
		this.size = size;
		this.allocator = allocator;
	}

	@SafeVarargs
	public static <R> List<R> ArrayList(R... array) {
		return ArrayList(DefaultAllocator_, (Object[]) array);
	}

	public static <R> List<R> ArrayList(Allocator allocator, Object... array) {
		return new ArrayList<>(array, array.length, allocator);
	}

	public static <R> ArrayList<R> ArrayList() {
		return ArrayList(DefaultAllocator_);
	}

	public static <R> ArrayList<R> ArrayList(Allocator allocator) {
		Object[] array = allocator.allocate(DEFAULT_SIZE);
		return new ArrayList<>(array, 0, allocator);
	}

	@Override
	public Object[] toArray() {
		Object[] copy = allocator.allocate(size);
		if (size >= 0) System.arraycopy(elements, 0, copy, 0, size);
		return copy;
	}

	private Object[] resize(Object[] oldList, int index) {
		int oldLength = oldList.length;
		int newLength = oldLength == 0 ? 1 : oldLength;
		while(newLength <= index) {
			newLength = round(newLength * GROWTH_FACTOR);
		}
		Object[] newArray = allocator.allocate(newLength);
		System.arraycopy(oldList, 0, newArray, 0, oldLength);
		return newArray;
	}

	@Override
	public List<T> set(int index, Object value) throws IndexException {
		if (index < 0) {
			String format = "Index '%d' cannot be negative.";
			String message = format.formatted(index);
			throw new IndexException(message);
		}

		Object[] list = resize(elements, index);
		list[index] = value;
		int elementCount = Math.max(index + 1, size);
		return new ArrayList<>(list, elementCount, allocator);
	}

	@Override
	public List<T> add(Object value) {
		try {
			return set(size, value);
		} catch (IndexException e) {
			return this;
		}
	}

	@Override
	public List<T> asEmpty() {
		return ArrayList(allocator);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T get(int index) throws IndexException {
		if (index < 0) throw new IndexException("Index '%d' is negative.".formatted(index));
		if (index >= size)
			throw new IndexException("Index '%d' exceeds size of '%d'.".formatted(index, size));
		return (T) elements[index];
	}
}
