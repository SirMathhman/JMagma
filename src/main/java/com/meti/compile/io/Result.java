package com.meti.compile.io;

import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.Sequences;
import com.meti.api.magma.collect.Stream;
import com.meti.api.magma.core.Option;

public interface Result {
	Option<Mapping> apply(Source source);

	int count();

	Sequence<Source> listSources();

	default Stream<Source> streamSources() {
		var sources = listSources();
		var stream = Sequences.stream(sources);
		return stream;
	}

	interface Mapping {
		String apply(Format name);

		Sequence<Format> listFormats();

		default Stream<Format> streamFormats() {
			return Sequences.stream(listFormats());
		}
	}

	interface Format {
		String format(String name);
	}
}
