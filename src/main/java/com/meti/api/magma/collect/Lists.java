package com.meti.api.magma.collect;

import java.util.ArrayList;

public class Lists {
	public Lists() {
	}

	public static <T> java.util.List<T> fromJava(List<T> list) {
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
