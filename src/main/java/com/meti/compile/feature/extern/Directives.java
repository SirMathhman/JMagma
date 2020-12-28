package com.meti.compile.feature.extern;

import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.Objects;
import java.util.Optional;

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
}
