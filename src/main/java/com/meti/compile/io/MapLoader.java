package com.meti.compile.io;

import com.meti.api.magma.io.IOException_;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapLoader implements Loader{
	private final Map<Source, String> content;

	public MapLoader(Map<Source, String> content) {
		this.content = content;
	}

	@Override
	public List<Source> listSources() throws IOException_ {
		return new ArrayList<>(content.keySet());
	}

	@Override
	public String load(Source source) throws IOException_ {
		if(!content.containsKey(source)) {
			var format = "Source '%s' doesn't exist.";
			var message = format.formatted(source);
			throw new IOException_(message);
		}
		return content.get(source);
	}
}
