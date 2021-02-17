package com.meti.compile.app;

import com.meti.compile.lex.CompoundLexer;
import com.meti.compile.lex.Lexer;
import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.feature.assign.AssignmentLexer.AssignmentLexer_;
import static com.meti.compile.feature.declare.DeclarationLexer.DeclarationLexer_;
import static com.meti.compile.feature.integer.IntegerLexer.IntegerLexer_;

public class MagmaNodeLexer extends CompoundLexer {
	public static final Lexer<Token> MagmaNodeLexer_ = new MagmaNodeLexer();

	public MagmaNodeLexer() {
	}

	@Override
	public List<Lexer<Token>> listLexers() {
		return List.of(DeclarationLexer_,
				AssignmentLexer_,
				IntegerLexer_);
	}
}
