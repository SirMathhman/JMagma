package com.meti.api.java.collect;

import com.meti.api.magma.collect.*;

import java.util.ArrayList;
import java.util.Collections;

public class JavaLists {
	public JavaLists() {
	}

	public static <T> List<T> empty() {
		return fromJava(Collections.emptyList());
	}

	public static <T> List<T> fromJava(java.util.List<T> value) {
		var copy = ArrayLists.<T>empty();
		try {
			var size = value.size();
			for (int i = 0; i < size; i++) {
				copy = copy.add(value.get(i));
			}
			return copy;
		} catch (CollectionException e) {
			return copy;
		}
	}

	public static <T> java.util.List<T> toJava(Sequence<T> sequence) {
		var copy = new ArrayList<T>();
		for (int i = 0; i < sequence.size(); i++) {
			try {
				copy.add(sequence.apply(i));
			} catch (IndexException e) {
				throw new UnsupportedOperationException();
			}
		}
		return copy;
	}
}
