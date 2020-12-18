package com.meti.compile.feature.function;

import com.meti.api.core.EF1;
import com.meti.compile.feature.Node;

public class Return implements Node{
	private final Node value;

	private Return(Node value) {
		this.value = value;
	}

	public static Return Return(Node value) {
		return new Return(value);
	}

	@Override
	public <E extends Exception> Node mapByChildren(EF1<Node, Node, E> mapper) throws E {
		return Return(mapper.apply(value));
	}

	@Override
	public String render() {
		return "return " + value.render() + ";";
	}
}
