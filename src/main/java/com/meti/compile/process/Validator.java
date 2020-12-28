package com.meti.compile.process;

import com.meti.compile.token.Node;
import com.meti.compile.script.Script;

public interface Validator extends ConditionalProcessor {
	@Override
	default Node processImpl(Script script, Node node) throws ParseException {
		validate(node);
		return node;
	}

	void validate(Node node) throws ParseException;
}
