package com.meti.api.collect;

import com.meti.api.memory.Allocator;

import static com.meti.api.memory.DefaultAllocator.DefaultAllocator_;
import static java.lang.Math.round;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_SIZE = 10;
	private static final float GROWTH_FACTOR = 1.5f;
	private final Object[] elements;
	private final int elementsSize;
	private final Allocator allocator;

	public ArrayList(Object[] elements, int elementsSize, Allocator allocator) {
		this.elements = elements;
		this.elementsSize = elementsSize;
		this.allocator = allocator;
	}

	@SafeVarargs
	public static <R> List<R> ArrayList(R... array) {
		return ArrayList(DefaultAllocator_, (Object[]) array);
	}

	public static <R> List<R> ArrayList(Allocator allocator, Object... array) {
		return new ArrayList<>(array, array.length, allocator);
	}

	public static <R> ArrayList<R> ArrayList(Allocator allocator) {
		Object[] array = allocator.allocate(DEFAULT_SIZE);
		return new ArrayList<>(array, 0, allocator);
	}

	@Override
	public Object[] toArray() {
		Object[] copy = allocator.allocate(elementsSize);
		if (elementsSize >= 0) System.arraycopy(elements, 0, copy, 0, elementsSize);
		return copy;
	}

	private Object[] resize(Object[] oldList, int index) {
		int oldLength = oldList.length;
		int newLength = oldLength == 0 ? 1 : oldLength;
		while (newLength <= index) {
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
		int elementCount = Math.max(index + 1, elementsSize);
		return new ArrayList<>(list, elementCount, allocator);
	}

	@Override
	public List<T> add(Object value) {
		try {
			return set(elementsSize, value);
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
		return elementsSize;
	}

	@Override
	public T get(int index) throws IndexException {
		if (index < 0) throw new IndexException("Index '%d' is negative.".formatted(index));
		if (index >= elementsSize)
			throw new IndexException("Index '%d' exceeds elementsSize of '%d'.".formatted(index, elementsSize));
		return (T) elements[index];
	}
}
