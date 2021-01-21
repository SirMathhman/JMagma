package com.meti.compile.io;

import java.util.List;
import java.util.Optional;

public interface Result {
	Optional<Mapping> apply(Source source);

	default int count() {
		return listSources().size();
	}

	List<Source> listSources();

	interface Mapping {
		String apply(Format name);

		List<Format> listFormats();
	}

	interface Format {
		String format(String name);
	}
}
