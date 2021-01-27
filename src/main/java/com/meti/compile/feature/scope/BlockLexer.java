package com.meti.compile.feature.scope;

import com.meti.compile.token.Token;

import java.util.List;
import java.util.stream.Collectors;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.compile.content.BracketSplitter.BracketSplitter_;

public class BlockLexer implements Lexer {
	public static final Lexer BlockLexer_ = new BlockLexer();

	private BlockLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("{") && line.endsWith("}");
	}

	@Override
	public Token lex(String content) {
		var length = content.length();
		var slice = content.substring(1, length - 1);
		var nodes = BracketSplitter_.stream(slice)
				.map(MagmaLexingStage_::lexNode)
				.collect(Collectors.toList());
		return new Block(nodes);
	}

	private static record Block(List<Token> nodes) implements Token {
		@Override
		public String render() {
			return nodes.stream()
					.map(Token::render)
					.collect(Collectors.joining("", "{", "}"));
		}
	}
}