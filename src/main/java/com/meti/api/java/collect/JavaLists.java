package com.meti.api.java.collect;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.List;

import java.util.ArrayList;
import java.util.Collections;

public class JavaLists {
	public JavaLists() {
	}

	public static <T> List<T> empty() {
		return new JavaList<>(Collections.emptyList());
	}

	public static <T> java.util.List<T> toJava(List<T> list) {
		var copy = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			try {
				copy.add(list.apply(i));
			} catch (IndexException e) {
				throw new UnsupportedOperationException();
			}
		}
		return copy;
	}
}
