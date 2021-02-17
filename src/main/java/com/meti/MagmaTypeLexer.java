package com.meti;

import com.meti.lex.CompoundLexer;
import com.meti.lex.Lexer;
import com.meti.token.Token;

import java.util.List;

import static com.meti.integer.IntegerTypeLexer.IntegerTypeLexer_;

public class MagmaTypeLexer extends CompoundLexer {
	public static final Lexer<Token> MagmaTypeLexer_ = new MagmaTypeLexer();

	@Override
	public List<Lexer<Token>> listLexers() {
		return List.of(IntegerTypeLexer_);
	}
}
