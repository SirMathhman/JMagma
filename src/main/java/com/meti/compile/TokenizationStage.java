package com.meti.compile;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Type;
import com.meti.compile.feature.field.Field;

import java.util.function.Supplier;

import static com.meti.compile.NodeTokenizer.NodeTokenizer_;
import static com.meti.compile.TokenizationException.TokenizationException;
import static com.meti.compile.TypeTokenizer.TypeTokenizer_;
import static com.meti.compile.feature.Node.Group.Content;
import static com.meti.compile.feature.content.ContentNode.ContentNode;

public class TokenizationStage implements Stage<String, Node> {
	static final Stage<String, Node> TokenizationStage_ = new TokenizationStage();

	private TokenizationStage() {
	}

	private Node tokenizeNode(Node node) throws TokenizationException {
		if (node.is(Content)) {
			Supplier<TokenizationException> noContent = () -> TokenizationException("""
					An instance of %s was marked as being content, but no actual content was found."""
					.formatted(node.getClass()));
			return tokenizeNodeContent(node.findContent().orElseThrow(noContent));
		} else {
			return node;
		}
	}

	private Node tokenizeNodeContent(String content) throws TokenizationException {
		return NodeTokenizer_.tokenize(content).orElseThrow(() -> TokenizationException("Unknown token: " + content));
	}

	private Node tokenizeTree(Node node) throws TokenizationException {
		return tokenizeNode(node)
				.mapByChildren(this::tokenizeNode)
				.mapByFields(this::tokenizeField)
				.mapByTypes(this::tokenizeType);
	}

	private Field tokenizeField(Field field) throws TokenizationException {
		return field.mapByType(TokenizationStage.this::tokenizeType);
	}

	private Type tokenizeType(Type type) throws TokenizationException {
		if (type.is(Type.Group.Content)) {
			Supplier<TokenizationException> noContent = () -> TokenizationException("""
					An instance of %s was marked as being content, but no actual content was found."""
					.formatted(type.getClass()));
			var content = type.findContent().orElseThrow(noContent);
			return tokenizeTypeContent(content);
		} else {
			return type;
		}
	}

	private Type tokenizeTypeContent(String content) throws TokenizationException {
		return TypeTokenizer_.tokenize(content).orElseThrow(() -> TokenizationException("Unknown token: " + content));
	}

	@Override
	public Node apply(String content) throws TokenizationException {
		return tokenizeTree(ContentNode(content));
	}
}
