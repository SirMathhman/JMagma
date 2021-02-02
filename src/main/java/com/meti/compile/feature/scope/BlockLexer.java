package com.meti.compile.feature.scope;

import com.meti.api.magma.collect.stream.StreamException;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Input;
import com.meti.compile.token.Output;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;
import static com.meti.compile.content.BracketSplitter.BracketSplitter_;

public class BlockLexer implements Lexer<Token> {
	public static final Lexer<Token> BlockLexer_ = new BlockLexer();

	private BlockLexer() {
	}

	private boolean canLex(String content) {
		return content.startsWith("{") && content.endsWith("}");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? Some.Some(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String content) {
		var length = content.length();
		var slice = content.substring(1, length - 1);
		try {
			return BracketSplitter_.stream(new Input(slice)).map(Input::getContent)
					.map(line -> MagmaLexingStage_.lexNode(new Input(line)))
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
		public Output render() {
			return new Output(nodes.stream()
					.map(token -> token.render().asString())
					.collect(Collectors.joining("", "{", "}")));
		}
	}
}