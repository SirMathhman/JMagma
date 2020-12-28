package com.meti.compile;

import com.meti.compile.feature.Node;
import com.meti.compile.process.ParseException;

public interface Validator extends ConditionalProcessor {
	@Override
	default Node processImpl(Script script, Node node) throws ParseException {
		validate(node);
		return node;
	}

	void validate(Node node) throws ParseException;
}
