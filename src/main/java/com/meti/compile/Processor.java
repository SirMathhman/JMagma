package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.compile.Script;
import com.meti.compile.feature.Node;
import com.meti.compile.process.ParseException;

public interface Processor {
	Option<Node> processOptionally(Script script, Node node) throws ParseException;
}
