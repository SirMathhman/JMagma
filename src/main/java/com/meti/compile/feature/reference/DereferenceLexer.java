package com.meti.compile.feature.reference;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Input;
import com.meti.compile.lex.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.lex.MagmaLexingStage.MagmaLexingStage_;

public class DereferenceLexer implements Lexer<Token> {
	public static final DereferenceLexer DereferenceLexer_ = new DereferenceLexer();

	private DereferenceLexer() {
	}

	private boolean canLex(String content) {
		return content.startsWith("*");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? Some.Some(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = MagmaLexingStage_.lexNode(new Input(string)).render().asString();
		return new Content("*%s".formatted(node));
	}
}