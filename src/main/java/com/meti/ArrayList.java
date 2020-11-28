package com.meti;

public class ArrayList<T> implements List<T> {
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

	public static <T> ArrayList<T> ArrayList() {
		return ArrayList(DEFAULT_CAPACITY);
	}

	public static <T> ArrayList<T> ArrayList(int capacity) {
		return new ArrayList<>(new Object[capacity], capacity, 0);
	}

	@Override
	public <R> List<R> asEmpty() {
		return ArrayList();
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
		if (index < 0) throw new IndexException("Index %d cannot be negative.".formatted(index));
		if (index >= size) {
			String format = "Index %d cannot be greater or equal to the number of elements available.";
			String message = format.formatted(index);
			throw new IndexException(message);
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

	public List<T> set(int index, T value) throws IndexException {
		return bounded(index, (Function1<Integer, List<T>>) integer -> {
			Object[] copy = resizeTo(integer);
			Object previous = copy[index];
			copy[index] = value;
			int newSize = previous == null ? size + 1 : size;
			return new ArrayList<>(copy, copy.length, newSize);
		});
	}

	@Override
	public List<T> add(T t) {
		try {
			return set(size, t);
		} catch (IndexException e) {
			return this;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
}
