package com.meti.api.java.collect;

import com.meti.api.magma.collect.IndexException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class JavaList<T> implements com.meti.api.magma.collect.List<T> {
	private final List<T> value;

	public JavaList(List<T> value) {
		this.value = value;
	}

	@Override
	public com.meti.api.magma.collect.List<T> add(T element) {
		var copy = new ArrayList<>(value);
		copy.add(element);
		return JavaLists.fromJava(copy);
	}

	@Override
	public com.meti.api.magma.collect.List<T> set(int index, T element) throws IndexException {
		var copy = new ArrayList<>(value);
		copy.set(index, element);
		return JavaLists.fromJava(copy);
	}

	@Override
	public T apply(int index) throws IndexException {
		if (index < 0) {
			var format = "Index of '%d' is negative.";
			var message = format.formatted(index);
			throw new IndexException(message);
		} else if (index >= value.size()) {
			var format = "Index of '%d' is greater than or equal to size of '%d'.";
			var message = format.formatted(index, value.size());
			throw new IndexException(message);
		} else {
			return value.get(index);
		}
	}

	@Override
	public int size() {
		return value.size();
	}

	public List<T> value() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (JavaList) obj;
		return Objects.equals(this.value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "JavaList[" +
		       "value=" + value + ']';
	}

}
