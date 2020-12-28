package com.meti.compile.stage;

import com.meti.compile.token.TokenizationException;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;
import com.meti.compile.feature.field.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.meti.compile.util.BracketSplitter.BracketSplitter_;
import static com.meti.compile.token.TokenizationException.TokenizationException;
import static com.meti.compile.token.Node.Group.Content;
import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static com.meti.compile.stage.NodeTokenizer.NodeTokenizer_;
import static com.meti.compile.stage.TypeTokenizer.TypeTokenizer_;

public class MagmaTokenizationStage implements TokenizationStage {
	public static final TokenizationStage MagmaTokenizationStage_ = new MagmaTokenizationStage();

	private MagmaTokenizationStage() {
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

	private Node tokenizeNodeTree(Node node) throws TokenizationException {
		return tokenizeNode(node)
				.mapByChildrenExceptionally(this::tokenizeNodeTree)
				.mapByFieldsExceptionally(this::tokenizeField)
				.mapByTypes(this::tokenizeTypeTree);
	}

	private Field tokenizeField(Field field) throws TokenizationException {
		return field.mapByType(MagmaTokenizationStage.this::tokenizeTypeTree);
	}

	private Type tokenizeTypeTree(Type type) throws TokenizationException {
		return tokenizeTypeParent(type).mapByChildren(this::tokenizeTypeTree);
	}

	private Type tokenizeTypeParent(Type type) throws TokenizationException {
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
	public Node tokenizeSingle(String content) throws TokenizationException {
		return tokenizeNodeTree(ContentNode(content));
	}

	@Override
	public List<Node> tokenizeAll(String content) throws TokenizationException {
		var lines = BracketSplitter_.split(content);
		var nodes = new ArrayList<Node>();
		for (String line : lines) {
			nodes.add(tokenizeSingle(line));
		}
		if (nodes.isEmpty()) throw TokenizationException("No nodes were found.");
		return nodes;
	}
}
