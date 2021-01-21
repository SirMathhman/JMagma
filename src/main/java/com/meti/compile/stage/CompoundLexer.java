package com.meti.compile.stage;

import com.meti.compile.CompileException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CompoundLexer<T> implements Lexer<T> {
	@Override
	public Optional<T> lex(String content) throws CompileException {
		var lexers = listLexers();
		for (Lexer<T> lexer : lexers) {
			var option = lexer.lex(content);
			if (option.isPresent()) {
				return option;
			}
		}
		return Optional.empty();
	}

	protected abstract List<Lexer<T>> listLexers();
}
