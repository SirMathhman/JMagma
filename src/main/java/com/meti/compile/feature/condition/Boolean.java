package com.meti.compile.feature.condition;

import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.Objects;
import java.util.Optional;

public class Boolean implements Node {
	public static final Node True = new Boolean(true);
	public static final Node False = new Boolean(false);
	private final boolean value;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Boolean aBoolean = (Boolean) o;
		return value == aBoolean.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	private Boolean(boolean value) {
		this.value = value;
	}

	public static Boolean Boolean(boolean value) {
		return new Boolean(value);
	}

	@Override
	public String render() {
		return value ? "1" : "0";
	}

	@Override
	public Option<Field> findIdentity() {
		return findIdentity2()
				.map(Some::Some)
				.orElseGet(None::None);
	}

	@Deprecated
	private Optional<Field> findIdentity2() {
		return Optional.empty();
	}
}
