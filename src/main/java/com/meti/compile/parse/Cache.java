package com.meti.compile.parse;

import com.meti.compile.token.Token;
import com.meti.compile.token.attribute.Attribute;

import java.util.List;

public interface Cache<T> {
	Cache<T> apply(State newState);

	Cache<T> apply(Attribute.Name name, State newState);

	Token apply(T indicator);

	List<T> listIndicators();

	State bind(Token token);
}
