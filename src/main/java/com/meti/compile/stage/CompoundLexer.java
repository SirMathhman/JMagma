package com.meti.compile.stage;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.CompileException;

import java.util.List;
import java.util.Optional;

public abstract class CompoundLexer<T> implements Lexer<T> {
	@Override
	public Option<T> lex(String content) throws CompileException {
		return lex1(content).map(Some::Some).orElseGet(None::None);
	}

	private Optional<T> lex1(String content) throws CompileException {
		var lexers = listLexers();
		for (Lexer<T> lexer : lexers) {
			var option = lexer.lex(content).map(Optional::of).orElseGet(Optional::empty);
			if (option.isPresent()) {
				return option;
			}
		}
		return Optional.empty();
	}

	protected abstract List<Lexer<T>> listLexers();
}
