package com.meti.compile.feature.block;

import com.meti.api.magma.collect.CollectionException;
import com.meti.api.magma.core.Option;
import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.api.magma.core.Some.Some;
import static com.meti.compile.content.BracketSplitter.BracketSplitter_;

public class BlockLexer implements Lexer<Token> {
	public static final Lexer<Token> BlockLexer_ = new BlockLexer();

	private BlockLexer() {
	}

	@Override
	public Option<Token> lex(String content) throws CompileException {
		return Some(content)
				.filter(this::isBlock)
				.mapE1(this::prepareContent);
	}

	private boolean isBlock(String content) {
		return content.startsWith("{") && content.endsWith("}");
	}

	private Token prepareContent(String content) throws CompileException {
		var length = content.length();
		var linesSlice = content.substring(1, length - 1);
		var linesString = linesSlice.trim();
		return formatLines(linesString);
	}

	private Token formatLines(String lines) throws CompileException {
		try {
			return formatLinesExceptionally(lines);
		} catch (CollectionException e) {
			throw new CompileException(e);
		}
	}

	private Token formatLinesExceptionally(String lines) throws CollectionException {
		return BracketSplitter_.stream(lines)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(Content::new)
				.fold(Blocks.Empty, Blocks.Builder::add)
				.complete();
	}
}
