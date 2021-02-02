package com.meti.compile.feature.scope;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class DeclarationLexer implements Lexer<Token> {
	public static final DeclarationLexer DeclarationLexer_ = new DeclarationLexer();

	private DeclarationLexer() {
	}

	private boolean canLex(String content) {
		return content.contains(":") && content.contains("=");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? new Some<>(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		return new Content("%s;".formatted(MagmaLexingStage_.lexField(line).render()));
	}
}