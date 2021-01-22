package com.meti.compile.io;

import com.meti.api.magma.collect.List;
import com.meti.api.magma.core.Option;

public interface Result {
	Option<Mapping> apply(Source source);

	int count();

	List<Source> listSources();

	interface Mapping {
		String apply(Format name);

		List<Format> listFormats();
	}

	interface Format {
		String format(String name);
	}
}
