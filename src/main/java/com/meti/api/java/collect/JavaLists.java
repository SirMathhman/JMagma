package com.meti.api.java.collect;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.collect.Sequence;

import java.util.ArrayList;
import java.util.Collections;

public class JavaLists {
	public JavaLists() {
	}

	public static <T> List<T> empty() {
		return new JavaList<>(Collections.emptyList());
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
