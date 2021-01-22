package com.meti.compile.feature.primitive;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Token;

import java.util.Optional;

public class IntegerLexer implements Lexer<Token> {
	public static final Lexer<Token> IntegerLexer_ = new IntegerLexer();

	private IntegerLexer() {
	}

	@Override
	public Option<Token> lex(String content) throws CompileException {
		return lex1(content).map(Some::Some).orElseGet(None::None);
	}

	private Optional<Token> lex1(String content) throws CompileException {
		var length = content.length();
		for (int i = 0; i < length; i++) {
			var c = content.charAt(i);
			if (!Character.isDigit(c)) {
				return Optional.empty();
			}
		}
		return Optional.of(new Integer(content));
	}
}
