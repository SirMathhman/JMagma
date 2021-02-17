package com.meti;

public class State {
	private final Stack stack;
	private final Token current;

	public State(Stack stack, Token current) {
		this.stack = stack;
		this.current = current;
	}

	public Token getCurrent() {
		return current;
	}

	public Stack getStack() {
		return stack;
	}

	public State withCurrent(Token other) {
		return new State(stack, other);
	}

	public State withStack(Stack stack) {
		return new State(stack, current);
	}
}
