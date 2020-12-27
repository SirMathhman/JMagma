package com.meti.compile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public record ListScript(List<String> parent, String name) implements Script {
	@Override
	public Stream<String> streamParents() {
		return parent().stream();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ListScript that = (ListScript) o;
		return Objects.equals(parent, that.parent) && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parent);
	}

	@Override
	public String name() {
		return name;
	}
}
