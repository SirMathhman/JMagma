package com.meti.api.magma.collect;

public record ArrayList<T>(Object[] elements, int elementSize) implements List<T> {
	@Override
	public List<T> add(T element) throws CollectionException {
		try {
			return set(elementSize, element);
		} catch (IndexException e) {
			throw new CollectionException(e);
		}
	}

	@Override
	public List<T> set(int index, T element) throws IndexException {
		if (index < 0) {
			var format = "Index of '%d' is negative.";
			var message = format.formatted(index);
			throw new IndexException(message);
		}

		var newElements = index < elements.length ? elements : resize(index);
		var newSize = index + 1 > elementSize ? index + 1 : elementSize;
		newElements[index] = element;
		return new ArrayList<>(newElements, newSize);
	}

	public Object[] resize(int index) {
		var newCapacity = calculateCapacity(index);
		var copy = new Object[newCapacity];
		for (int i = 0; i < elementSize; i++) copy[i] = elements[i];
		return copy;
	}

	private int calculateCapacity(int index) {
		var oldCapacity = elements.length;
		var current = oldCapacity == 0 ? 1 : oldCapacity;
		while (current <= index) {
			current *= 2;
		}
		return current;
	}

	@Override
	public T apply(int index) throws IndexException {
		if (index < 0) {
			var format = "Index of '%d' is negative.";
			var message = format.formatted(index);
			throw new IndexException(message);
		}
		if (index >= elementSize) {
			var format = "Index of '%d' is greater than or equal to length.";
			var message = format.formatted(index);
			throw new IndexException(message);
		}
		return (T) elements[index];
	}

	@Override
	public int size() {
		return elementSize;
	}
}
