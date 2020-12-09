package com.meti.exec.compile;

public class EmptyMapResult implements Result<Result.Group> {
	public static final EmptyMapResult EmptyMapResult_ = new EmptyMapResult();

	private EmptyMapResult() {
	}

	@Override
	public Result<Group> with(Group group, String result) {
		return this;
	}

	@Override
	public String apply(Group group) {
		return "";
	}
}
