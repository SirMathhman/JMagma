package com.meti.compile.io;

import com.meti.api.magma.collect.List;
import com.meti.api.magma.io.IOException_;

public interface Loader {
	List<Source> listSources() throws IOException_;

	String load(Source source) throws IOException_;
}
