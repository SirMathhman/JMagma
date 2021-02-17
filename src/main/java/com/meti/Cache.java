package com.meti;

import java.util.ArrayList;
import java.util.List;

public class Cache {
	private final Stack stack;
	private final List<Token> children;

	public Cache(Stack stack, List<Token> children) {
		this.stack = stack;
		this.children = children;
	}

	public Cache apply(F1<State, State> applicator) {
		Stack stack = new MapStack();
		var newChildren = new ArrayList<Token>();
		for (Token child : children) {
			var oldState = new State(stack, child);
			var newState = applicator.apply(oldState);
			stack = newState.getStack();
			newChildren.add(newState.getCurrent());
		}
		return new Cache(stack, newChildren);
	}

	public List<Token> getChildren() {
		return children;
	}

	public Stack getStack() {
		return stack;
	}
}
