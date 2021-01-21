package com.meti.compile.stage;

import com.meti.api.magma.core.F1E1;
import com.meti.compile.CompileException;
import com.meti.compile.token.*;

import java.util.ArrayList;
import java.util.Optional;

import static com.meti.compile.stage.NodeLexer.NodeLexer_;
import static com.meti.compile.stage.TypeLexer.TypeLexer_;
import static com.meti.compile.token.Attribute.Type.*;

public class MagmaLexerStage implements SingleStage<String, Token> {
	public static final SingleStage<String, Token> MagmaLexerStage_ = new MagmaLexerStage();

	public MagmaLexerStage() {
	}

	@Override
	public Token apply(String s) throws CompileException {
		return lexNodeString(s);
	}

	private Token fold(Token token, Attribute.Type group, F1E1<Attribute, Attribute, CompileException> mapping) throws CompileException {
		var list = token.list(group);
		var current = token;
		for (Token.Query query : list) {
			var attribute = token.apply(query);
			var tokenAttribute = mapping.apply(attribute);
			current = current.copy(query, tokenAttribute);
		}
		return current;
	}

	private Token lexContent(Token node, F1E1<String, Token, CompileException> mapper) throws CompileException {
		if (node.apply(Token.Query.Group) == GroupAttribute.Content) {
			var attribute = node.apply(Token.Query.Value);
			var value = attribute.asString();
			return mapper.apply(value);
		} else {
			return node;
		}
	}

	private com.meti.compile.token.Field lexField(com.meti.compile.token.Field field) throws CompileException {
		var type = field.findType();
		var newType = lexTypeContent(type);
		return field.withType(newType);
	}

	private FieldAttribute lexFieldAttribute(Attribute attribute) throws CompileException {
		return new FieldAttribute(lexField(attribute.asField()));
	}

	private FieldListAttribute lexFieldListAttribute(Attribute attribute) throws CompileException {
		var oldList = attribute.asFieldList();
		var newList = new ArrayList<Field>();
		for (Field field : oldList) {
			newList.add(lexField(field));
		}
		return new FieldListAttribute(newList);
	}

	private Token lexNodeContent(Token node) throws CompileException {
		return lexContent(node, this::lexNodeString);
	}

	private Token lexNodeString(String value) throws CompileException {
		return lexString(value, NodeLexer_::lex);
	}

	private Token lexString(String content, F1E1<String, Optional<Token>, CompileException> mapper) throws CompileException {
		var optional = mapper.apply(content);
		var token = optional.orElseThrow(() -> new CompileException("Bad token: " + content));

		var withNodes = fold(token, Node, attribute -> lexTokenAttribute(attribute, this::lexNodeContent));
		var withNodeLists = fold(withNodes, NodeList, attribute -> lexTokenListAttribute(attribute, this::lexNodeContent));
		var withTypes = fold(withNodeLists, Type, attribute -> lexTokenAttribute(attribute, this::lexTypeContent));
		var withTypeList = fold(withTypes, TypeList, attribute -> lexTokenListAttribute(attribute, this::lexTypeContent));
		var withField = fold(withTypeList, Field_, this::lexFieldAttribute);
		return fold(withField, FieldList, this::lexFieldListAttribute);
	}

	private Attribute lexTokenAttribute(Attribute attribute, F1E1<Token, Token, CompileException> mapper) throws CompileException {
		var node = attribute.asToken();
		var newNode = mapper.apply(node);
		return new TokenAttribute(newNode);
	}

	private Attribute lexTokenListAttribute(Attribute attribute, F1E1<Token, Token, CompileException> mapper) throws CompileException {
		var nodes = attribute.asTokenList();
		var newNodes = new ArrayList<Token>();
		for (Token node : nodes) newNodes.add(mapper.apply(node));
		return new TokenListAttribute(newNodes);
	}

	private Token lexTypeContent(Token type) throws CompileException {
		return lexContent(type, this::lexTypeString);
	}

	private Token lexTypeString(String value) throws CompileException {
		return lexString(value, TypeLexer_::lex);
	}
}
