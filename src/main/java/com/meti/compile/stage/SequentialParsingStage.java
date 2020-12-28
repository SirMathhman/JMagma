package com.meti.compile.stage;

import com.meti.api.core.Option;
import com.meti.compile.process.Processor;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;
import com.meti.compile.process.ParseException;

import java.util.List;

import static com.meti.api.core.None.None;

public abstract class SequentialParsingStage implements RecursiveParsingStage {
	@Override
	public Option<Node> processContents(Script script, Node node) throws ParseException {
		var processors = listProcessors();
		for (Processor processor : processors) {
			var noneOption = processor.processOptionally(script, node);
			if (noneOption.isPresent()) {
				return noneOption;
			}
		}
		return None();
	}

	protected abstract List<Processor> listProcessors();
}
