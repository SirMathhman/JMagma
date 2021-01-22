package com.meti.compile.io;

import com.meti.api.java.collect.JavaList;
import com.meti.api.magma.collect.Lists;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record MapResult(Map<Source, Mapping> mappings) implements Result {
	@Override
	public Option<Mapping> apply(Source source) {
		return apply1(source)
				.map(Some::Some)
				.orElseGet(None::None);
	}

	private Optional<Mapping> apply1(Source source) {
		if (mappings.containsKey(source)) {
			return Optional.of(mappings.get(source));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public int count() {
		return Lists.toJava(listSources()).size();
	}

	@Override
	public com.meti.api.magma.collect.List<Source> listSources() {
		return new JavaList<>(listSources1());
	}

	private List<Source> listSources1() {
		return new ArrayList<>(mappings.keySet());
	}
}
