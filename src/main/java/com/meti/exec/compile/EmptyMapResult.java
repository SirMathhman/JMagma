package com.meti.exec.compile;

public class EmptyMapResult implements Result {
	public static final EmptyMapResult EmptyMapResult_ = new EmptyMapResult();

	private EmptyMapResult() {
	}

	@Override
	public String apply(Group group) {
		return "";
	}
}
