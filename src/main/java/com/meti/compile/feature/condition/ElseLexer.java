package com.meti.compile.feature.condition;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Input;
import com.meti.compile.lex.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.lex.MagmaLexingStage.MagmaLexingStage_;

public class ElseLexer implements Lexer<Token> {
	public static final Lexer<Token> ElseLexer_ = new ElseLexer();

	private ElseLexer() {
	}

	private boolean canLex(String content) {
		return content.startsWith("else");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? Some.Some(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		var bodySlice = line.substring(4);
		var bodyString = bodySlice.trim();
		return new Content("else%s".formatted(MagmaLexingStage_.lexNode(new Input(bodyString)).render().getValue()));
	}
}
