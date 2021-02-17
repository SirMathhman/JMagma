package com.meti.compile.token;

import com.meti.core.F1;
import com.meti.core.F1E1;

import java.util.List;
import java.util.Optional;

public class EmptyField extends AbstractField {
	public EmptyField(List<Flag> flags, Input name, Token type) {
		super(flags, name, type);
	}

	@Override
	public <R> Optional<R> applyToValue(F1<Token, R> mapper) {
		return Optional.empty();
	}

	@Override
	public <R, E extends Exception> Optional<R> applyToValueE1(F1E1<Token, R, E> mapper) throws E {
		return Optional.empty();
	}

	@Override
	public <E extends Exception> Field mapByValue(F1E1<Token, Token, E> mapper) throws E {
		return this;
	}

	@Override
	public <E extends Exception> Field copyByType(Token newType) throws E {
		return new EmptyField(flags, name, newType);
	}
}
