package com.meti.compile;

import java.util.List;
import java.util.stream.Stream;

public class ListScript implements Script {
	private final List<String> args;

	private ListScript(List<String> args) {
		this.args = args;
	}

	public static ListScript ListScript(List<String> args) {
		return new ListScript(args);
	}

	@Override
	public Stream<String> stream() {
		return args.stream();
	}
}
