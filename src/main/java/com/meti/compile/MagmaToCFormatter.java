package com.meti.compile;

import java.util.List;

import static com.meti.compile.feature.extern.ImportFormatter.ImportFormatter_;
import static com.meti.compile.feature.function.InvocationFormatter.InvocationFormatter_;
import static com.meti.compile.feature.function.NativeFunctionRemover.NativeFunctionRemover_;

public class MagmaToCFormatter extends SequentialParsingStage {
	static final ParsingStage MagmaToCFormatter_ = new MagmaToCFormatter();

	private MagmaToCFormatter() {
	}

	@Override
	protected List<Processor> listProcessors() {
		return List.of(ImportFormatter_,
				NativeFunctionRemover_,
				InvocationFormatter_);
	}
}