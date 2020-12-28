package com.meti.compile.process;

import com.meti.api.core.Option;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;

public interface Processor {
	Option<Node> processOptionally(Script script, Node node) throws ParseException;
}
