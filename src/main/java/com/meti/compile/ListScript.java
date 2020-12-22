package com.meti.compile;

import java.util.List;
import java.util.stream.Stream;

public record ListScript(List<String> args) implements Script {

	@Override
	public Stream<String> streamAll() {
		return args.stream();
	}

	@Override
	public Stream<String> streamParents() {
		return args.subList(0, args.size() - 1).stream();
	}

	@Override
	public String name() {
		return args.get(args.size() - 1);
	}
}
