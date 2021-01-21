package com.meti.compile.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record MapResult(Map<Source, Mapping> mappings) implements Result {
	@Override
	public Mapping apply(Source source) {
		return mappings.get(source);
	}

	@Override
	public List<Source> listSources() {
		return new ArrayList<>(mappings.keySet());
	}
}
