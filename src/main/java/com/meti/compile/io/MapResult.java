package com.meti.compile.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record MapResult(Map<Source, Mapping> mappings) implements Result {
	@Override
	public Optional<Mapping> apply(Source source) {
		if (mappings.containsKey(source)) {
			return Optional.of(mappings.get(source));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Source> listSources() {
		return new ArrayList<>(mappings.keySet());
	}
}
