package com.meti.compile.io;

import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.io.IOException_;

public interface Loader {
	Sequence<Source> listSources() throws IOException_;

	String load(Source source) throws IOException_;
}
