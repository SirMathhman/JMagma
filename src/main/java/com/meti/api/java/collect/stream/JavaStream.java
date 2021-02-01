package com.meti.api.java.collect.stream;

import com.meti.api.java.core.JavaOption;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.api.magma.core.F1E1;
import com.meti.api.magma.core.F2E1;
import com.meti.api.magma.core.Option;

import java.util.stream.Stream;

public record JavaStream<T>(Stream<T> value) implements com.meti.api.magma.collect.stream.Stream<T> {
	@Override
	public boolean allMatch(F1E1<T, Boolean, ?> predicate) throws StreamException {
		return value.allMatch(t -> {
			try {
				return predicate.apply(t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public com.meti.api.magma.collect.stream.Stream<T> filter(F1E1<T, Boolean, ?> predicate) {
		return new JavaStream<>(value.filter(t -> {
			try {
				return predicate.apply(t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}));
	}

	@Override
	public <R> R fold(R identity, F2E1<R, T, R, ?> folder) {
		return value.reduce(identity, (r, t) -> {
			try {
				return folder.apply(r, t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}, (r, r2) -> r2);
	}

	@Override
	public Option<T> fold(F2E1<T, T, T, ?> folder) {
		return new JavaOption<>(getValue().reduce((s, s2) -> {
			try {
				return folder.apply(s, s2);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}));
	}

	@Override
	public <R> com.meti.api.magma.collect.stream.Stream<R> map(F1E1<T, R, ?> mapper) {
		return new JavaStream<>(getValue().map(line -> {
			try {
				return mapper.apply(line);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}));
	}

	public Stream<T> getValue() {
		return value;
	}
}
