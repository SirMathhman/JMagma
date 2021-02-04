package com.meti.api.magma.collect.list;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.api.magma.collect.string.Stringable;
import com.meti.api.magma.core.F2;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.content.Equatable;

public class Lists {
	public Lists() {
	}

	public static <T extends Equatable<T>> boolean equalsTo(List<T> self, List<T> other) {
		return equalsTo(self, other, Equatable::equalsTo);
	}

	public static <T> boolean equalsTo(List<T> self, List<T> other, F2<T, T, Boolean> equator) {
		var selfSize = self.size();
		var otherSize = other.size();
		if (selfSize == otherSize) {
			for (int i = 0; i < selfSize; i++) {
				try {
					var selfElement = self.apply(i);
					var otherElement = self.apply(i);
					if (!equator.apply(selfElement, otherElement)) {
						return false;
					}
				} catch (IndexException e) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static <T extends Stringable> String asString(List<T> self) {
		try {
			return self.stream()
					.map(Stringable::asString)
					.fold((s, s2) -> s + s2)
					.map("[%s]"::formatted)
					.orElse("[]");
		} catch (StreamException e) {
			return "";
		}
	}

	public static <T extends Equatable<T>> boolean contains(List<T> self, T element) {
		return contains(self, element, Equatable::equalsTo);
	}

	public static <T> boolean contains(List<T> self, T element, F2<T, T, Boolean> equator) {
		return findFirst(self, element, equator).isPresent();
	}

	private static <T> Option<Integer> findFirst(List<T> self, T element, F2<T, T, Boolean> equator) {
		try {
			for (int i = 0; i < self.size(); i++) if (equator.apply(self.apply(i), element)) return new Some<>(i);
			return new None<>();
		} catch (IndexException e) {
			return new None<>();
		}
	}
}
