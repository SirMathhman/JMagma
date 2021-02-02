package com.meti.compile;

import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;
import com.meti.compile.token.Output;
import com.meti.compile.token.Token;

import static com.meti.compile.lex.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.compile.content.BracketSplitter.BracketSplitter_;

public class MagmaCompiler implements Compiler {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	@Override
	public Output compile(Input input) throws CompileException {
		try {
			return new Output(compileImpl(input));
		} catch (StreamException e) {
			throw new CompileException(e);
		}
	}

	private String compileImpl(Input input) throws StreamException {
		return BracketSplitter_.stream(input)
				.map(MagmaLexingStage_::lexNode)
				.map(Token::render)
				.map(Output::asString)
				.fold((current, next) -> current + next)
				.orElse("");
	}
}