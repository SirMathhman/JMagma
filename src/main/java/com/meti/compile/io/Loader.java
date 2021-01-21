package com.meti.compile.io;

import com.meti.api.magma.io.IOException_;

import java.util.List;

public interface Loader {
	List<Source> listScripts() throws IOException_;

	String load(Source source) throws IOException_;
}
