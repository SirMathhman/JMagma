package com.meti;

import com.meti.attribute.Attribute;
import com.meti.attribute.AttributeException;
import com.meti.lex.LexException;
import com.meti.lex.Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.meti.MagmaNodeLexer.MagmaNodeLexer_;
import static com.meti.MagmaTypeLexer.MagmaTypeLexer_;

public class MagmaLexingStage {
	public static final MagmaLexingStage MagmaLexingStage_ = new MagmaLexingStage();

	public MagmaLexingStage() {
	}

	private Token handleChildren(Token node) throws LexException {
		var withChildren = lexAttributes(node, Attribute.Type.Node, foldFromLexer(MagmaNodeLexer_));
		var withTypes = lexAttributes(withChildren, Attribute.Type.Type, foldFromLexer(MagmaTypeLexer_));
		return lexAttributes(withTypes, Attribute.Type.Field, createFolder());
	}

	private F2E1<Token, Attribute.Name, Token, LexException> createFolder() {
		return (token, name) -> {
			try {
				return token.mapByField(name, this::handleField);
			} catch (AttributeException e) {
				throw new LexException(e);
			}
		};
	}

	private F2E1<Token, Attribute.Name, Token, LexException> foldFromLexer(final Lexer<Token> lex) {
		return (token, name) -> {
			try {
				return token.mapByToken(name, token2 -> lexContent(token2, lex));
			} catch (AttributeException e) {
				throw new LexException(e);
			}
		};
	}

	private Field handleField(Field field) throws LexException {
		return field.mapByType(this::handleType)
				.mapByValue(this::handleNode);
	}

	private Token handleNode(Token token) throws LexException {
		return lexContent(token, MagmaNodeLexer_);
	}

	private Token handleType(Token token) throws LexException {
		return lexContent(token, MagmaTypeLexer_);
	}

	List<Token> lex(Input input) throws LexException {
		var lines = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int i = 0; i < input.getContent().length(); i++) {
			var c = input.getContent().charAt(i);
			if (c == '}' && depth == 1) {
				buffer.append('}');
				lines.add(buffer.toString());
				buffer = new StringBuilder();
				depth -= 1;
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
		var newLines = new ArrayList<Token>();
		for (String line : lines) {
			var input1 = new Input(line);
			var token = lexInput(input1, MagmaNodeLexer_);
			newLines.add(token);
		}
		return newLines;
	}

	private <T> Token lexAttributes(Token node, Attribute.Type type, F2E1<Token, Attribute.Name, Token, LexException> folder) throws LexException {
		var children = node.stream(type).collect(Collectors.toList());
		var withChildren = node;
		for (Attribute.Name child : children) {
			withChildren = folder.apply(withChildren, child);

		}
		return withChildren;
	}

	private Token lexContent(Token token, Lexer<Token> lexer) throws LexException {
		try {
			if (Tokens.is(token, Token.Type.Content)) {
				var attribute = token.apply(Attribute.Name.Value);
				var input = attribute.computeInput();
				return lexInput(input, lexer);
			}
			return token;
		} catch (AttributeException e) {
			throw new LexException(e);
		}
	}

	private Token lexInput(Input input, Lexer<Token> lexer) throws LexException {
		var optional = lexer.lex(input);
		var node = optional.orElseThrow(() -> new LexException("Invalid: " + input));
		return handleChildren(node);
	}
}
