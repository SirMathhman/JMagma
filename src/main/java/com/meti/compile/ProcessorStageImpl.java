package com.meti.compile;

import com.meti.compile.feature.EmptyNode;
import com.meti.compile.feature.Line;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.extern.ImportProcessor;
import com.meti.compile.feature.extern.ScriptProcessor;
import com.meti.compile.feature.field.Field;
import com.meti.compile.process.ProcessException;

import java.util.ArrayList;
import java.util.List;

public class ProcessorStageImpl implements ProcessorStage {
	private final ScriptProcessor scriptProcessor = new ImportProcessor();

	public ProcessorStageImpl() {
	}

	public Node processChild(Script current, Node node) throws ProcessException {
		Node newNode;
		if (node.is(Node.Group.Import)) {
			newNode = scriptProcessor.process(current, node);
		} else if (node.is(Node.Group.Function)) {
			var identity = node.findIdentity().orElseThrow(() -> new ProcessException(node + " was a function but didn't have an identity."));
			if (identity.isFlagged(Field.Flag.NATIVE)) {
				newNode = EmptyNode.EmptyNode_;
			} else {
				newNode = node;
			}
		} else if (node.is(Node.Group.Block)) {
			newNode = node.mapByChildren(child -> {
				if (child.is(Node.Group.Invocation)) {
					return Line.Line(child);
				} else {
					return child;
				}
			});
		} else {
			newNode = node;
		}
		return newNode.mapByChildrenExceptionally(node1 -> processChild(current, node1));
	}

	@Override
	public List<Node> process(Script script, List<Node> nodes) throws ProcessException {
		var post = new ArrayList<Node>();
		for (Node node : nodes) {
			post.add(processChild(script, node));
		}
		return post;
	}
}