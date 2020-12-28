package com.meti.compile.script;

import com.meti.api.core.Option;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.meti.api.core.Some.Some;

public record StringSource(String value) implements Source {
	@Override
	public Option<String> read(Script script) throws IOException {
		return Some(value);
	}

	@Override
	public List<Script> list() throws IOException {
		return List.of(new ListScript(Collections.emptyList(), "Main"));
	}
}
