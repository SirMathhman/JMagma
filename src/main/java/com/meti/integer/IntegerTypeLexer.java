package com.meti.integer;

import com.meti.Input;
import com.meti.LexException;
import com.meti.Lexer;
import com.meti.Token;

import java.util.Optional;

public class IntegerTypeLexer implements Lexer<Token> {
	public static final Lexer<Token> IntegerTypeLexer_ = new IntegerTypeLexer();

	public IntegerTypeLexer() {
	}

	@Override
	public Optional<Token> lex(Input input) throws LexException {
		var isSigned = input.test(value -> value.startsWith("I"));
		var isUnsigned = input.test(value -> value.startsWith("U"));
		if (isSigned || isUnsigned) {
			var bits = input.slice(1).peek(java.lang.Integer::parseInt);
			var type = isSigned ?
					IntegerType.signed(bits) :
					IntegerType.unsigned(bits);
			return Optional.of(type);
		}
		return Optional.empty();
	}
}
