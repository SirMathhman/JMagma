package com.meti.compile.stage;

import com.meti.api.java.collect.JavaLists;
import com.meti.compile.stack.Stack;
import com.meti.compile.token.Token;

import java.util.List;
import java.util.Objects;

public final class Context {
	private final Stack stack;
	private final com.meti.api.magma.collect.List<Token> nodes;

	public Context(Stack stack, com.meti.api.magma.collect.List<Token> nodes) {
		this.stack = stack;
		this.nodes = nodes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stack, nodes);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Context) obj;
		return Objects.equals(this.stack, that.stack) &&
		       Objects.equals(this.nodes, that.nodes);
	}

	@Override
	public String toString() {
		return "Context[" +
		       "stack=" + stack + ", " +
		       "nodes=" + nodes + ']';
	}

	public List<Token> nodes() {
		return JavaLists.toJava(nodes);
	}
}
