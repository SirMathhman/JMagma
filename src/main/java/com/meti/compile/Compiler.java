package com.meti.compile;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;
import com.meti.compile.process.ProcessException;

import java.util.ArrayList;
import java.util.EnumMap;

import static com.meti.compile.TokenizationException.TokenizationException;
import static com.meti.compile.TokenizationStage.TokenizationStage_;
import static com.meti.compile.feature.EmptyNode.EmptyNode_;
import static com.meti.compile.feature.Line.Line;
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
			newList.add(process(node));
		}
		var cache = new Cache<>(new EnumMap<>(Type.class));
		for (Node node : newList) {
			cache = attach(cache, node);
		}
		return cache.render();
	}

	private Node process(Node node) throws ProcessException {
		Node newNode;
		if (node.is(Node.Group.Function)) {
			var identity = node.findIdentity()
					.orElseThrow(() -> new ProcessException(node + " was a function but didn't have an identity."));
			if (identity.isFlagged(Field.Flag.NATIVE)) {
				newNode = EmptyNode_;
			} else {
				newNode = node;
			}
		} else if (node.is(Node.Group.Block)) {
			newNode = node.mapByChildren(child -> {
				if (child.is(Node.Group.Invocation)) {
					return Line(child);
				} else {
					return child;
				}
			});
		} else {
			newNode = node;
		}
		return newNode.mapByChildrenExceptionally(this::process);
	}

	private Cache<Type> attach(Cache<Type> cache, Node node) {
		Cache<Type> newCache;
		if (node.is(Structure)) {
			newCache = cache.put(Type.Structure, node);
		} else if (node.is(Function)) {
			newCache = cache.put(Type.Function, node);
		} else {
			newCache = cache.put(Type.Other, node);
		}
		return newCache;
	}

	enum Type {
		Structure,
		Function,
		Other
	}
}