package com.meti.compile.stage;

import com.meti.compile.CompileException;

public class MagmaFlatteningStage {
	public static final MagmaFlatteningStage MagmaFlatteningStage_ = new MagmaFlatteningStage();

	private MagmaFlatteningStage() {
	}

	public Context apply(Context context) throws CompileException {
		return context;
	}
}
