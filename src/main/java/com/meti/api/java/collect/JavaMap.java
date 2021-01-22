package com.meti.api.java.collect;

import com.meti.api.magma.collect.List;
import com.meti.api.magma.core.Option;
import com.meti.compile.io.Result;
import com.meti.compile.io.Source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.meti.api.magma.core.Some.Some;

public final class JavaMap {
	private final Map<Source, Result.Mapping> mappings;

	public JavaMap() {
		this(Collections.emptyMap());
	}

	public JavaMap(Map<Source, Result.Mapping> mappings) {
		this.mappings = mappings;
	}

	public Option<Result.Mapping> apply(Source source) {
		return Some(source)
				.filter(mappings::containsKey)
				.map(mappings::get);
	}

	public List<Source> listKeys() {
		return new JavaList<>(new ArrayList<>(mappings.keySet()));
	}

	public JavaMap put(Source key, Result.Mapping value) {
		var copy = new HashMap<>(mappings);
		copy.put(key, value);
		return new JavaMap(copy);
	}

	public int size() {
		return mappings.size();
	}
}
