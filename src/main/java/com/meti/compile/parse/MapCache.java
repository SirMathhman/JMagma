package com.meti.compile.parse;

import com.meti.compile.token.Token;
import com.meti.compile.token.attribute.Attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapCache extends AbstractCache<Attribute.Name> {
	private final Map<Attribute.Name, Token> children;

	public MapCache(Stack stack) {
		this(stack, new HashMap<>());
	}

	public MapCache(Stack stack, Map<Attribute.Name, Token> children) {
		super(stack);
		this.children = children;
	}

	@Override
	public Cache<Attribute.Name> apply(State newState) {
		children.put(Attribute.Name.Value, newState.getCurrent());
		return new MapCache(newState.getStack(), children);
	}

	@Override
	public Cache<Attribute.Name> apply(Attribute.Name name, State newState) {
		children.put(name, newState.getCurrent());
		return new MapCache(newState.getStack(), children);
	}

	@Override
	public Token apply(Attribute.Name indicator) {
		return children.get(indicator);
	}

	@Override
	public List<Attribute.Name> listIndicators() {
		return new ArrayList<>(children.keySet());
	}

	@Override
	public State bind(Token token) {
		return new State(stack, token);
	}
}
