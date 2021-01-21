package com.meti.compile.stage;

import com.meti.compile.CompileException;
import com.meti.compile.io.Result;
import com.meti.compile.token.Token;

import java.util.List;

public class CRenderStage implements SingleStage<List<Token>, Result.Mapping> {
	public static final SingleStage<List<Token>, Result.Mapping> CRenderStage_ = new CRenderStage();

	private CRenderStage() {
	}

	@Override
	public Result.Mapping apply(List<Token> tokens) throws CompileException {
		return null;
	}
}
