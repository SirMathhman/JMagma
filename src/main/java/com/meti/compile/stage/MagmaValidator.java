package com.meti.compile.stage;

import com.meti.compile.process.Processor;

import java.util.List;

import static com.meti.compile.feature.scope.DeclarationValidator.DeclarationValidator_;

public class MagmaValidator extends SequentialParsingStage {
	public static final ParsingStage MagmaValidator_ = new MagmaValidator();

	private MagmaValidator() {
	}

	@Override
	protected List<Processor> listProcessors() {
		return List.of(
				DeclarationValidator_
		);
	}
}
