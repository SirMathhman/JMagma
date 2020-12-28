package com.meti.compile.stage;

import com.meti.compile.process.Processor;

import java.util.List;

import static com.meti.compile.feature.function.FunctionParser.FunctionParser_;
import static com.meti.compile.feature.scope.DeclarationParser.DeclarationParser_;

public class MagmaParser extends SequentialParsingStage {
	public static final ParsingStage MagmaParser_ = new MagmaParser();

	public MagmaParser() {
	}

	@Override
	protected List<Processor> listProcessors() {
		return List.of(FunctionParser_, DeclarationParser_);
	}
}
