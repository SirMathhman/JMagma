package com.meti.compile.feature.extern;

import com.meti.api.core.Option;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;

import java.util.Objects;

import static com.meti.api.core.Some.Some;

public record Import(Script script) implements Node {

	static Import Import(Script script) {
		return new Import(script);
	}

	@Override
	public boolean is(Group group) {
		return group == Group.Import;
	}

	@Override
	public Option<Script> findScript() {
		return Some(script);
	}

	@Override
	public String toString() {
		return "Import{" +
		       "script=" + script +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Import anImport = (Import) o;
		return Objects.equals(script, anImport.script);
	}

	@Override
	public int hashCode() {
		return Objects.hash(script);
	}

	@Override
	public String render() {
		return "???";
	}
}
