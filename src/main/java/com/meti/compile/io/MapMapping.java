package com.meti.compile.io;

import com.meti.compile.token.Attribute;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record MapMapping(Map<Result.Format, List<Token>> map) implements Result.Mapping {
	@Override
	public String apply(Result.Format name) {
		return map.get(name)
				.stream()
				.map(token -> token.apply(Token.Query.Value))
				.map(Attribute::asString)
				.collect(Collectors.joining());
	}

	@Override
	public List<Result.Format> listFormats() {
		return new ArrayList<>(map.keySet());
	}

	public MapMapping with(Result.Format format, Token rendered) {
		var copy = new HashMap<>(map);
		if (!copy.containsKey(format)) {
			copy.put(format, new ArrayList<>());
		}
		copy.get(format).add(rendered);
		return new MapMapping(copy);
	}
}
