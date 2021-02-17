package com.meti.compile.app;

import com.meti.compile.CompileException;
import com.meti.compile.token.Input;

import static com.meti.compile.app.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.compile.app.MagmaParsingStage.MagmaParsingStage_;
import static com.meti.compile.app.MagmaRenderStage.MagmaRenderStage_;

public class MagmaCompiler {
	public static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	public MagmaCompiler() {
	}

	public String compile(Input input) throws CompileException {
		var token = MagmaLexingStage_.lex(input);
		var processed = MagmaParsingStage_.parse(token);
		return MagmaRenderStage_.render(processed);
	}
}
