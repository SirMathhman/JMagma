package com.meti.compile.script;

import com.meti.api.core.Option;

import java.io.IOException;
import java.util.List;

public interface Source {
	Option<String> read(Script script) throws IOException;

	List<Script> list() throws IOException;
}
