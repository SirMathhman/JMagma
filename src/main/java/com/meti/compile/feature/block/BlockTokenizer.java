package com.meti.compile.feature.block;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;
import com.meti.compile.feature.content.ContentNode;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

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
			var list = new ArrayList<String>();
			var buffer = new StringBuilder();
			var depth = 0;
			for (int i = 0; i < trim.length(); i++) {
				var c = trim.charAt(i);
				if (c == '}' && depth == 1) {
					buffer.append('}');
					depth--;
					list.add(buffer.toString());
					buffer = new StringBuilder();
				} else if (c == ';' && depth == 0) {
					list.add(buffer.toString());
					buffer = new StringBuilder();
				} else {
					if (c == '{') {
						depth++;
					} else if (c == '}') {
						depth--;
					}
					buffer.append(c);
				}
			}
			list.add(buffer.toString());
			list.removeIf(String::isBlank);
			var nodeList = list.stream()
					.map(String::trim)
					.map(ContentNode::ContentNode)
					.collect(Collectors.toList());
			return Optional.of(Block(nodeList));
		}
		return Optional.empty();
	}
}
