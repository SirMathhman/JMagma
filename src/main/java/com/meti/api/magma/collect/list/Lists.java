package com.meti.api.magma.collect.list;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.core.F2;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.content.Equatable;

public class Lists {
	public Lists() {
	}

	public static <T extends Equatable<T>> boolean contains(List<T> list, T element) {
		return contains(list, element, Equatable::equalsTo);
	}

	public static <T> boolean contains(List<T> list, T element, F2<T, T, Boolean> equator) {
		return findFirst(list, element, equator).isPresent();
	}

	private static <T> Option<Integer> findFirst(List<T> list, T element, F2<T, T, Boolean> equator) {
		try {
			for (int i = 0; i < list.size(); i++) if (equator.apply(list.apply(i), element)) return new Some<>(i);
			return new None<>();
		} catch (IndexException e) {
			return new None<>();
		}
	}
}
