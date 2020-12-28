package com.meti.compile.feature.function;

import com.meti.api.core.Option;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;
import com.meti.compile.process.Processor;
import com.meti.compile.process.ParseException;

import java.util.function.Function;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.compile.feature.Line.Line;

public class InvocationFormatter implements Processor {
	public static final InvocationFormatter InvocationFormatter_ = new InvocationFormatter();

	private InvocationFormatter() {
	}

	private Node process(Node node) {
		Function<Node, Node> nodeNodeFunction = child -> processImpl(child).orElse(child);
		return node.mapByChildren2(nodeNodeFunction);
	}

	@Override
	public Option<Node> processOptionally(Script script, Node node) throws ParseException {
		return node.is(Node.Group.Invocation) ? Some(process(node)) : None();
	}

	private Option<Node> processImpl(Node child) {
		if (child.is(Node.Group.Invocation)) {
			return Some(Line(child));
		} else {
			return None();
		}
	}
}