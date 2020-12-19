package com.meti.compile.feature.extern;

import com.meti.compile.feature.Node;

import java.util.Objects;

public enum Directive {
	Include;

	Node toNode(String value) {
		return new DirectiveNode(this, value);
	}

	private static class DirectiveNode implements Node {
		private final String value;
		private final Directive directive;

		public DirectiveNode(Directive directive, String value) {
			this.value = value;
			this.directive = directive;
		}

		@Override
		public String toString() {
			return "DirectiveNode{" +
			       "value='" + value + '\'' +
			       ", directive=" + directive +
			       '}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			DirectiveNode that = (DirectiveNode) o;
			return Objects.equals(value, that.value) &&
			       directive == that.directive;
		}

		@Override
		public int hashCode() {
			return Objects.hash(value, directive);
		}

		@Override
		public String render() {
			return "#" + directive.name().toLowerCase() + " " + value + "\n";
		}
	}
}
