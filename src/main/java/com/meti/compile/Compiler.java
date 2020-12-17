package com.meti.compile;

import static com.meti.compile.TokenizationStage.TokenizationStage_;

public class Compiler {
	String compile(String content) throws CompileException {
		return TokenizationStage_.apply(content).render();
	}
}