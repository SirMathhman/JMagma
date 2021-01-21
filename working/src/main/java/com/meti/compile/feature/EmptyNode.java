package com.meti.compile.feature;

public class EmptyNode implements Node {
	public static final Node EmptyNode_ = new EmptyNode();

	private EmptyNode() {
	}

	@Override
	public String render() {
		return "";
	}
}
