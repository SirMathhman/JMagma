package com.meti.compile.stage;

import com.meti.compile.CompileException;

public class MagmaFlatteningStage implements SingleStage<StageState, StageState> {
	public static final SingleStage<StageState, StageState> MagmaFlatteningStage_ = new MagmaFlatteningStage();

	private MagmaFlatteningStage() {
	}

	@Override
	public StageState apply(StageState stageState) throws CompileException {
		return null;
	}
}
