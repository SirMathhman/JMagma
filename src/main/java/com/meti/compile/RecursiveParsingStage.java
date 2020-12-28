package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.compile.feature.Node;
import com.meti.compile.process.ParseException;

import java.util.ArrayList;
import java.util.List;

public interface RecursiveParsingStage extends ParsingStage {
	private Node processRoot(Script script, Node node) throws ParseException {
		return processContents(script, node)
				.orElse(node)
				.mapByChildrenExceptionally(child -> processRoot(script, child));
	}

	Option<Node> processContents(Script script, Node node) throws ParseException;

	@Override
	default List<Node> process(Script script, List<Node> nodes) throws ParseException {
		var post = new ArrayList<Node>();
		for (Node node : nodes) {
			post.add(processRoot(script, node));
		}
		return post;
	}
}
