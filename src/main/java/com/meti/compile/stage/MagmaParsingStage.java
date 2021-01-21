package com.meti.compile.stage;

import com.meti.compile.CompileException;

public class MagmaParsingStage implements SingleStage<StageState, StageState> {
	public static final SingleStage<StageState, StageState> MagmaParsingStage_ = new MagmaParsingStage();

	private MagmaParsingStage() {
	}

	@Override
	public StageState apply(StageState stageState) throws CompileException {
		return stageState;
	}
}
