package com.meti.compile.io;

import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.core.Option;

public interface Result {
	Option<Mapping> apply(Source source);

	int count();

	Sequence<Source> listSources();

	interface Mapping {
		String apply(Format name);

		Sequence<Format> listFormats();
	}

	interface Format {
		String format(String name);
	}
}
