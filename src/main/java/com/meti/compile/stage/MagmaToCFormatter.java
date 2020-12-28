package com.meti.compile.stage;

import com.meti.compile.process.Processor;

import java.util.List;

import static com.meti.compile.feature.extern.ImportFormatter.ImportFormatter_;
import static com.meti.compile.feature.function.InvocationFormatter.InvocationFormatter_;
import static com.meti.compile.feature.function.NativeFunctionRemover.NativeFunctionRemover_;

public class MagmaToCFormatter extends SequentialParsingStage {
	public static final ParsingStage MagmaToCFormatter_ = new MagmaToCFormatter();

	private MagmaToCFormatter() {
	}

	@Override
	protected List<Processor> listProcessors() {
		return List.of(ImportFormatter_,
				NativeFunctionRemover_,
				InvocationFormatter_);
	}
}