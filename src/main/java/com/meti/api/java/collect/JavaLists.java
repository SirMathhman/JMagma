package com.meti.api.java.collect;

import com.meti.api.magma.collect.list.ArrayLists;

import java.util.List;

public class JavaLists {
	public JavaLists() {
	}

	public static <T> com.meti.api.magma.collect.list.List<T> fromJava(List<T> lines) {
		return lines.stream().reduce(ArrayLists.empty(), com.meti.api.magma.collect.list.List::add, (tList, tList2) -> tList2);
	}
}
