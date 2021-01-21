package com.meti.compile.io;

import java.util.List;

public interface Result {
	Mapping apply(Source source);

	List<Source> listSources();

	interface Mapping {
		String apply(Format name);

		List<Format> listFormats();
	}

	interface Format {
		String format(String name);
	}
}
