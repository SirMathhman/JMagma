package com.meti.compile;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;
import com.meti.compile.process.ProcessException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.meti.compile.TokenizationException.TokenizationException;
import static com.meti.compile.TokenizationStage.TokenizationStage_;
import static com.meti.compile.feature.EmptyNode.EmptyNode_;
import static com.meti.compile.feature.Node.Group.Function;
import static com.meti.compile.feature.Node.Group.Structure;

public class Compiler {
	String compile(String content) throws CompileException {
		var list = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int i = 0; i < content.length(); i++) {
			var c = content.charAt(i);
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
		var nodes = new ArrayList<Node>();
		for (String s : list) {
			nodes.add(TokenizationStage_.apply(s));
		}
		if (nodes.isEmpty()) throw TokenizationException("No nodes were found.");
		var newList = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node.is(Node.Group.Function)) {
				var identity = node.findIdentity()
						.orElseThrow(() -> new ProcessException(node + " was a function but didn't have an identity."));
				if (identity.isFlagged(Field.Flag.NATIVE)) {
					newList.add(EmptyNode_);
				} else {
					newList.add(node);
				}
			} else {
				newList.add(node);
			}
		}
		var map = new EnumMap<Group, List<Node>>(Group.class);
		for (Node node : newList) {
			if (node.is(Structure)) {
				put(map, Group.Structure, node);
			} else if (node.is(Function)) {
				put(map, Group.Function, node);
			} else {
				put(map, Group.Other, node);
			}
		}
		return map.keySet()
				.stream()
				.sorted((o1, o2) -> -o1.compareTo(o2))
				.map(map::get)
				.flatMap(List::stream)
				.map(Node::render)
				.collect(Collectors.joining());
	}

	private void put(EnumMap<Group, List<Node>> map, Group group, Node node) {
		if (!map.containsKey(group)) {
			map.put(group, new ArrayList<>());
		}
		map.get(group).add(node);
	}

	enum Group {
		Structure,
		Function,
		Other
	}
}