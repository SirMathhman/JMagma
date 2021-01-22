package com.meti.compile.stage;

import com.meti.compile.CompileException;

public class MagmaFlatteningStage implements SingleStage<Context, Context> {
	public static final SingleStage<Context, Context> MagmaFlatteningStage_ = new MagmaFlatteningStage();

	private MagmaFlatteningStage() {
	}

	@Override
	public Context apply(Context context) throws CompileException {
		return context;
	}
}
