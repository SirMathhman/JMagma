package com.meti.compile.feature.function;

import com.meti.compile.feature.Line;
import com.meti.compile.process.ConditionalProcessor;
import com.meti.compile.process.ParseException;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;

public class InvocationFormatter implements ConditionalProcessor {
	public static final InvocationFormatter InvocationFormatter_ = new InvocationFormatter();

	private InvocationFormatter() {
	}

	@Override
	public Node processImpl(Script script, Node node) throws ParseException {
		return node.mapByChildren(child -> {
			if (child.is(Node.Group.Invocation)) {
				return new Line(child);
			} else {
				return child;
			}
		});
	}

	@Override
	public boolean canProcess(Script script, Node node) {
		return node.is(Node.Group.Block);
	}
}