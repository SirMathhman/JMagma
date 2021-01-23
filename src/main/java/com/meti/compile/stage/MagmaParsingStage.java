package com.meti.compile.stage;

import com.meti.compile.CompileException;

public class MagmaParsingStage {
	public static final MagmaParsingStage MagmaParsingStage_ = new MagmaParsingStage();

	private MagmaParsingStage() {
	}

	public Context apply(Context context) throws CompileException {
		return context;
	}
}
