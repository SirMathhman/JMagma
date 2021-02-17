package com.meti;

import static com.meti.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.MagmaParsingStage.MagmaParsingStage_;
import static com.meti.MagmaRenderStage.MagmaRenderStage_;

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
