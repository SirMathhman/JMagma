package com.meti.app;

import com.meti.CompileException;
import com.meti.token.Input;

import static com.meti.app.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.app.MagmaParsingStage.MagmaParsingStage_;
import static com.meti.app.MagmaRenderStage.MagmaRenderStage_;

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
