package com.meti.compile.feature.scope;

import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.Collections;
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
		try {
			return BracketSplitter_.stream(slice)
					.map(MagmaLexingStage_::lexNode)
					.fold(new Builder(Collections.emptyList()), Builder::add)
					.complete();
		} catch (StreamException e) {
			return new Block(Collections.emptyList());
		}
	}

	private static record Builder(List<Token> lines) {
		Builder add(Token line) {
			var copy = new ArrayList<>(lines);
			copy.add(line);
			return new Builder(copy);
		}

		Token complete() {
			return new Block(lines);
		}
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