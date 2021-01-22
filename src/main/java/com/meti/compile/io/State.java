package com.meti.compile.io;

import com.meti.api.magma.collect.Sequence;
import com.meti.compile.stack.Stack;
import com.meti.compile.stage.Context;
import com.meti.compile.token.Token;

public class State {
	private final MapResults.Builder result;
	private final Stack stack;

	public State(MapResults.Builder result, Stack stack) {
		this.result = result;
		this.stack = stack;
	}

	public Context attach(Sequence<Token> tokens) {
		return new Context(stack, tokens);
	}

	public Result complete() {
		return result.complete();
	}

	public State reset(Sequence<Source> sequence) {
		return withStack(stack.reset(sequence));
	}

	public State withStack(Stack stack) {
		return new State(result, stack);
	}

	public State with(Source source, Result.Mapping mapping) {
		return new State(result.with(source, mapping), stack);
	}
}
