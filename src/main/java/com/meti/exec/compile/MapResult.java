package com.meti.exec.compile;

import com.meti.api.collect.map.ListMap;
import com.meti.api.collect.map.Map;

public class MapResult implements Result<Result.Group> {
	private final Map<Group, String> value;

	public MapResult() {
		this(new ListMap<>());
	}

	public MapResult(Map<Group, String> value) {
		this.value = value;
	}

	@Override
	public Result<Group> with(Group group, String result) {
		return new MapResult(value.put(group, result));
	}

	@Override
	public String apply(Group group) {
		return value.get(group).orElse("");
	}
}
