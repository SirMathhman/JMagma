package com.meti.compile;

import com.meti.api.magma.collect.stream.StreamException;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.compile.content.BracketSplitter.BracketSplitter_;

public class MagmaCompiler implements Compiler {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	@Override
	public String compile(String content) {
		try {
			return BracketSplitter_.stream(content)
					.map(line -> MagmaLexingStage_.lexNode(line).render())
					.fold((current, next) -> current + next)
					.orElse("");
		} catch (StreamException e) {
			return "";
		}
	}
}