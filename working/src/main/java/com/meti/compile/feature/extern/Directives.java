package com.meti.compile.feature.extern;

import com.meti.compile.feature.Node;

import java.util.Objects;

public enum Directives {
	Include;

	public Node toNode(String value) {
		return new NodeImpl(this, value);
	}

	private static record NodeImpl(Directives directives, String value) implements Node {
		@Override
		public String toString() {
			return "DirectiveNode{" +
			       "value='" + value + '\'' +
			       ", directive=" + directives +
			       '}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			NodeImpl that = (NodeImpl) o;
			return Objects.equals(value, that.value) &&
			       directives == that.directives;
		}

		@Override
		public int hashCode() {
			return Objects.hash(value, directives);
		}

		@Override
		public String render() {
			return "#" + directives.name().toLowerCase() + " " + value + "\n";
		}

		@Override
		public boolean is(Group group) {
			return group == Group.Directive;
		}
	}
}
