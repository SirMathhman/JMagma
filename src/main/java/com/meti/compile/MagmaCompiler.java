package com.meti.compile;

import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;
import com.meti.compile.token.Output;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.compile.content.BracketSplitter.BracketSplitter_;

public class MagmaCompiler implements Compiler {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	@Override
	public Output compile(Input input) {
		try {
			return new Output(BracketSplitter_.stream(new Input(input.getContent())).map(Input::getContent)
					.map(line -> MagmaLexingStage_.lexNode(new Input(line)).render().getValue())
					.fold((current, next) -> current + next)
					.orElse(""));
		} catch (StreamException e) {
			return new Output("");
		}
	}
}