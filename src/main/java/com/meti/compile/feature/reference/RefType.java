package com.meti.compile.feature.reference;

import com.api.core.EF1;
import com.meti.compile.feature.Type;

import java.util.Objects;

public class RefType implements Type {
	private final Type child;

	private RefType(Type child) {
		this.child = child;
	}

	public static RefType RefType(Type child) {
		return new RefType(child);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RefType refType = (RefType) o;
		return Objects.equals(child, refType.child);
	}

	@Override
	public String toString() {
		return "RefType{" +
		       "child=" + child +
		       '}';
	}

	@Override
	public <E extends Exception> Type mapByChildren(EF1<Type, Type, E> mapper) throws E {
		return RefType(mapper.apply(child));
	}

	@Override
	public int hashCode() {
		return Objects.hash(child);
	}

	@Override
	public String render(String value) {
		return child.render("* " + value);
	}
}
