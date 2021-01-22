package com.meti.compile.stage;

import com.meti.compile.CompileException;

public class MagmaParsingStage implements SingleStage<Context, Context> {
	public static final SingleStage<Context, Context> MagmaParsingStage_ = new MagmaParsingStage();

	private MagmaParsingStage() {
	}

	@Override
	public Context apply(Context context) throws CompileException {
		return context;
	}
}
