package com.meti.exec.compile;

import java.util.HashMap;

public class MapResult implements Result {
	private final HashMap<Group, String> value;

	public MapResult(HashMap<Group, String> value) {
		this.value = value;
	}

	@Override
	public String apply(Group group) {
		return value.get(group);
	}
}
