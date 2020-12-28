package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.compile.feature.Node;
import com.meti.compile.process.ParseException;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public interface ConditionalProcessor extends Processor {
	Node processImpl(Script script, Node node) throws ParseException;

	boolean canProcess(Script script, Node node);

	@Override
	default Option<Node> processOptionally(Script script, Node node) throws ParseException {
		return canProcess(script, node) ? Some(processImpl(script, node)) : None();
	}
}
