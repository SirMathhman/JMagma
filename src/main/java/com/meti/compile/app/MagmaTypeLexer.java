package com.meti.compile.app;

import com.meti.compile.lex.CompoundLexer;
import com.meti.compile.lex.Lexer;
import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.integer.IntegerTypeLexer.IntegerTypeLexer_;

public class MagmaTypeLexer extends CompoundLexer {
	public static final Lexer<Token> MagmaTypeLexer_ = new MagmaTypeLexer();

	@Override
	public List<Lexer<Token>> listLexers() {
		return List.of(IntegerTypeLexer_);
	}
}
