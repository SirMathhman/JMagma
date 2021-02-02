package com.meti.compile.content;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.Streams;
import com.meti.compile.token.Input;

public class ParameterSplitter implements Splitter {
	public static final ParameterSplitter ParameterSplitter_ = new ParameterSplitter();

	private ParameterSplitter() {
	}

	@Override
	public Stream<Input> stream(Input input) {
		return Streams.of(input.getContent().split(",")).map(Input::new);
	}
}
