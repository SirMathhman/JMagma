package com.meti.compile.parse;

import com.meti.compile.token.Token;
import com.meti.compile.token.attribute.Attribute;

import java.util.ArrayList;
import java.util.List;

public class ListCache extends AbstractCache<Integer> {
	private final List<Token> tokens;

	public ListCache(Stack stack) {
		this(stack, new ArrayList<>());
	}

	public ListCache(Stack stack, List<Token> tokens) {
		super(stack);
		this.tokens = tokens;
	}

	@Override
	public Cache<Integer> apply(State newState) {
		tokens.add(newState.getCurrent());
		return new ListCache(newState.getStack(), tokens);
	}

	@Override
	public Cache<Integer> apply(Attribute.Name name, State newState) {
		tokens.add(newState.getCurrent());
		return new ListCache(newState.getStack(), tokens);
	}

	@Override
	public Token apply(Integer indicator) {
		return tokens.get(indicator);
	}

	@Override
	public List<Integer> listIndicators() {
		var list = new ArrayList<Integer>();
		for (int i = 0; i < tokens.size(); i++) {
			list.add(i);
		}
		return list;
	}

	@Override
	public State bind(Token token) {
		return new State(stack, token);
	}
}
