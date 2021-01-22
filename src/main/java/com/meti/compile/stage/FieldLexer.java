package com.meti.compile.stage;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.CompileException;

import java.util.Optional;

public class FieldLexer<T> implements Lexer<T> {
	@Override
	public Option<T> lex(String content) throws CompileException {
		return lex1(content).map(Some::Some).orElseGet(None::None);
	}

	private Optional<T> lex1(String content) {
		return Optional.empty();
	}
}
