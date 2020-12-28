package com.meti.compile.process;

import com.meti.api.core.Option;
import com.meti.compile.token.Node;
import com.meti.compile.script.Script;

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
