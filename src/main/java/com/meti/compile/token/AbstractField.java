package com.meti.compile.token;

import com.meti.core.F1;
import com.meti.core.F1E1;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractField implements Field {
	protected final List<Field.Flag> flags;
	protected final Token type;
	protected final Input name;

	public AbstractField(List<Flag> flags, Input name, Token type) {
		this.flags = flags;
		this.type = type;
		this.name = name;
	}

	@Override
	public <R> R applyToName(F1<Input, R> mapper) {
		return mapper.apply(name);
	}

	@Override
	public <R, E extends Exception> R applyToNameE1(F1E1<Input, R, E> mapper) throws E {
		return mapper.apply(name);
	}

	@Override
	public <R> R applyToType(F1<Token, R> mapper) {
		return mapper.apply(type);
	}

	@Override
	public <R, E extends Exception> R applyToTypeE1(F1E1<Token, R, E> mapper) throws E {
		return mapper.apply(type);
	}

	@Override
	public boolean isFlagged(Flag flag) {
		return flags.contains(flag);
	}

	@Override
	public boolean isNamed(String name) {
		return this.name.is(name);
	}

	@Override
	public <E extends Exception> Field mapByType(F1E1<Token, Token, E> mapper) throws E {
		return copyByType(mapper.apply(type));
	}

	public abstract <E extends Exception> Field copyByType(Token newType) throws E;

	@Override
	public <E extends Exception> boolean testTypeE1(F1E1<Token, Boolean, E> predicate) throws E {
		return predicate.apply(type);
	}

	@Override
	public Field withType(Token type) {
		return copyByType(type);
	}

	protected String joinFlags() {
		return flags.stream()
				.map(Flag::name)
				.map(String::toLowerCase)
				.collect(Collectors.joining(",", "[", "]"));
	}
}
