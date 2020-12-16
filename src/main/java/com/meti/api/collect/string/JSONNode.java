package com.meti.api.collect.string;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.list.List;
import com.meti.api.core.Equatable;
import com.meti.api.core.Stringable;

import static com.meti.api.collect.string.StringNode.StringNode;

public interface JSONNode extends Stringable, Equatable<JSONNode> {
	JSONNode.ArrayNode ArrayNode = Array(ArrayList.empty(JSONNode::equalsTo));
	JSONNode Empty = () -> "";

	static JSONNode.ArrayNode Array(List<JSONNode> children) {
		return new ArrayNode(children);
	}

	@Override
	default boolean equalsTo(JSONNode other) {
		return Strings.equalsTo(asString(), other.asString());
	}

	class ArrayNode implements JSONNode {
		private final List<JSONNode> children;

		private ArrayNode(List<JSONNode> children) {
			this.children = children;
		}

		@Override
		public String asString() {
			return "[" + stringifyChildren() + "]";
		}

		private String stringifyChildren() {
			return children.stream()
					.map(child -> child.asString())
					.foldLeft(StringBuffer.Empty, (buffer, s) -> buffer.add(", " + s))
					.asString();
		}

		public JSONNode.ArrayNode addString(String value) {
			return Array(children.add(StringNode(value)));
		}
	}
}
