package com.meti.compile.content;

import com.meti.api.java.collect.stream.JavaStream;
import com.meti.api.magma.collect.stream.Stream;
import com.meti.compile.token.Input;

import java.util.Arrays;

public class ParameterSplitter implements Splitter {
	public static final ParameterSplitter ParameterSplitter_ = new ParameterSplitter();

	private ParameterSplitter() {
	}

	@Override
	public Stream<Input> stream(Input input) {
		return stream2(input).map(Input::new);
	}

	private Stream<String> stream2(Input input) {
		return new JavaStream<>(Arrays.stream(input.getContent().split(",")));
	}
}
