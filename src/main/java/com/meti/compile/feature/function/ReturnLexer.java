package com.meti.compile.feature.function;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.feature.scope.Input;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class ReturnLexer implements Lexer<Token> {
	public static final ReturnLexer ReturnLexer_ = new ReturnLexer();

	private ReturnLexer() {
	}

	private boolean canLex(String content) {
		return content.startsWith("return ");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? new Some<>(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		var valueSlice = line.substring(7);
		var valueString = valueSlice.trim();
		var value = MagmaLexingStage_.lexNode(valueString).render();
		return new Content("return %s;".formatted(value));
	}
}