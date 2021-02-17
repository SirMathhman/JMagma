package com.meti;

import com.meti.lex.CompoundLexer;
import com.meti.lex.Lexer;
import com.meti.token.Token;

import java.util.List;

import static com.meti.declare.DeclarationLexer.DeclarationLexer_;
import static com.meti.integer.IntegerLexer.IntegerLexer_;

public class MagmaNodeLexer extends CompoundLexer {
	public static final Lexer<Token> MagmaNodeLexer_ = new MagmaNodeLexer();

	public MagmaNodeLexer() {
	}

	@Override
	public List<Lexer<Token>> listLexers() {
		return List.of(DeclarationLexer_, IntegerLexer_);
	}
}
