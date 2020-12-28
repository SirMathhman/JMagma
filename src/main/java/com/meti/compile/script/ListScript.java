package com.meti.compile.script;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public record ListScript(List<String> parent, String name) implements Script {
	@Override
	public Stream<String> streamParent() {
		return parent.stream();
	}

	@Override
	public List<String> listParent() {
		return parent;
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
