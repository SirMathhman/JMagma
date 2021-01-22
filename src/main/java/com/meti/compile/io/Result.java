package com.meti.compile.io;

import com.meti.api.magma.collect.Lists;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.Stream;
import com.meti.api.magma.core.Option;

public interface Result {
	Option<Mapping> apply(Source source);

	int count();

	Sequence<Source> listSources();

	default Stream<Source> streamSources() {
		var sources = listSources();
		var stream = Lists.stream(sources);
		return stream;
	}

	interface Mapping {
		String apply(Format name);

		Sequence<Format> listFormats();

		default Stream<Format> streamFormats() {
			return Lists.stream(listFormats());
		}
	}

	interface Format {
		String format(String name);
	}
}
