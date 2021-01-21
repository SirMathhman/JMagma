package com.meti.compile.feature.block;

import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.AbstractToken;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class BlockLexer implements Lexer<Token> {
	public static final Lexer<Token> BlockLexer_ = new BlockLexer();

	public BlockLexer() {
	}

	@Override
	public Optional<Token> lex(String content) throws CompileException {
		if (content.startsWith("{") && content.endsWith("}")) {
			var linesSlice = content.substring(1, content.length() - 1);
			var linesString = linesSlice.trim();

			var lines = new ArrayList<String>();
			var buffer = new StringBuilder();
			var depth = 0;
			for (int j = 0; j < linesString.length(); j++) {
				var c = linesString.charAt(j);
				if (c == '}' && depth == 1) {
					depth = 0;
					buffer.append('}');
					lines.add(buffer.toString());
					buffer = new StringBuilder();
				} else if (c == ';' && depth == 0) {
					lines.add(buffer.toString());
					buffer = new StringBuilder();
				} else {
					if (c == '{') depth++;
					if (c == '}') depth--;
					buffer.append(c);
				}
			}
			lines.add(buffer.toString());
			lines.removeIf(String::isBlank);
			var children = lines.stream()
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(Content::new)
					.collect(Collectors.<Token>toList());
			return Optional.of(new Block(children));
		}
		return Optional.empty();
	}
}
