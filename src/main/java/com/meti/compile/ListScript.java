package com.meti.compile;

import java.util.List;
import java.util.stream.Stream;

public class ListScript implements Script {
	private final List<String> args;

	public ListScript(List<String> args) {
		this.args = args;
	}

	@Override
	public Stream<String> stream() {
		return args.stream();
	}
}
