package com.meti.exec.compile;

import java.util.HashMap;

@Deprecated
public class MapResult implements Result<Result.Group> {
	private final HashMap<Group, String> value;

	public MapResult() {
		this(new HashMap<>());
	}

	public MapResult(HashMap<Group, String> value) {
		this.value = value;
	}

	@Override
	public Result<Group> with(Group group, String result) {
		value.put(group, result);
		return this;
	}

	@Override
	public String apply(Group group) {
		return value.get(group);
	}
}
