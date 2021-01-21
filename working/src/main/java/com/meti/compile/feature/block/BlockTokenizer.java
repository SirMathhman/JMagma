package com.meti.compile.feature.block;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;
import com.meti.compile.feature.content.ContentNode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.meti.compile.BracketSplitter.BracketSplitter_;
import static com.meti.compile.feature.block.Block.Block;

public class BlockTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> BlockTokenizer_ = new BlockTokenizer();

	private BlockTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) {
		if (content.startsWith("{") && content.endsWith("}")) {
			var slice = content.substring(1, content.length() - 1);
			var trim = slice.trim();
			var nodeList = tokenizeChildren(trim);
			return Optional.of(Block(nodeList));
		}
		return Optional.empty();
	}

	private List<ContentNode> tokenizeChildren(String trim) {
		return BracketSplitter_.split(trim)
				.stream()
				.map(String::trim)
				.map(ContentNode::ContentNode)
				.collect(Collectors.toList());
	}
}
