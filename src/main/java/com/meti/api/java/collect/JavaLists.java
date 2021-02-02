package com.meti.api.java.collect;

import java.util.List;

public class JavaLists {
	public JavaLists() {
	}

	public static <T, L extends List<T>> L add(L self, T element) {
		self.add(element);
		return self;
	}
}
