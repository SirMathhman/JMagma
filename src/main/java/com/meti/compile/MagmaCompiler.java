package com.meti.compile;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.compile.content.BracketSplitter.BracketSplitter_;

public class MagmaCompiler implements Compiler {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	public static Stream<String> splitSequence(String sequence) {
		return Arrays.stream(sequence.split(","));
	}

	@Override
	public String compile(String content) {
		return BracketSplitter_.stream(content)
				.map(line -> MagmaLexingStage_.lexNode(line).render())
				.collect(Collectors.joining());
	}
}