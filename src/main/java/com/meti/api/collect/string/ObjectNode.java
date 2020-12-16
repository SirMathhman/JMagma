package com.meti.api.collect.string;

import com.meti.api.collect.map.OrderedMap;
import com.meti.api.core.Option;
import com.meti.api.extern.Function1;

public class ObjectNode implements JSONNode {
	private final OrderedMap<String, JSONNode> internalMap;

	public ObjectNode(OrderedMap<String, JSONNode> internalMap) {
		this.internalMap = internalMap;
	}

	@Override
	public String asString() {
		return "";
	/*	internalMap.streamKeys()
				.map(new Function1<String, Option<String>>() {
					@Override
					public Option<String> apply(String s) {
						return internalMap.get(s)
								.map(JSONNode::asString)
								.map(s2 -> "\"" + s + "\":" + s2);
					}
				})
				.foldLeft(StringBuffer.Empty, StringBuffer.Empty::addS);*/
	}
}
