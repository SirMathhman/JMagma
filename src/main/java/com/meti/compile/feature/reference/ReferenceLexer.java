package com.meti.compile.feature.reference;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Input;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class ReferenceLexer implements Lexer<Token> {
	public static final ReferenceLexer ReferenceLexer_ = new ReferenceLexer();

	private ReferenceLexer() {
	}

	private boolean canLex(String content) {
		return content.startsWith("&");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? new Some<>(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = MagmaLexingStage_.lexNode(string).render().getValue();
		return new Content("&%s".formatted(node));
	}
}