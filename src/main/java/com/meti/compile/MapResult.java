package com.meti.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record MapResult<T>(Map<T, Cache<MagmaCompiler.CodeType>> map) implements Result<T> {
	@Override
	public List<T> listKeys() {
		return new ArrayList<>(map.keySet());
	}

	@Override
	public String renderToString(T type) {
		if (map.containsKey(type)) {
			var cache = map.get(type);
			return cache.render();
		} else {
			return "";
		}
	}
}
