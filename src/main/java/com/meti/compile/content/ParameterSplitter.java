package com.meti.compile.content;

import com.meti.api.java.collect.stream.JavaStream;
import com.meti.api.magma.collect.stream.Stream;

import java.util.Arrays;

public class ParameterSplitter implements Splitter {
	public static final ParameterSplitter ParameterSplitter_ = new ParameterSplitter();

	private ParameterSplitter() {
	}

	@Override
	public Stream<String> stream(String sequence) {
		return new JavaStream<>(Arrays.stream(sequence.split(",")));
	}
}
