package com.meti.compile;

import com.meti.api.core.Supplier;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.extern.ImportProcessor;
import com.meti.compile.feature.extern.ScriptProcessor;
import com.meti.compile.feature.field.Field;
import com.meti.compile.process.ProcessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static com.meti.compile.BracketSplitter.BracketSplitter_;
import static com.meti.compile.TokenizationException.TokenizationException;
import static com.meti.compile.TokenizationStage.TokenizationStage_;
import static com.meti.compile.feature.EmptyNode.EmptyNode_;
import static com.meti.compile.feature.Line.Line;
import static com.meti.compile.feature.Node.Group.Function;
import static com.meti.compile.feature.Node.Group.Structure;

public class MagmaCompiler implements Compiler {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();
	private final ScriptProcessor scriptProcessor = new ImportProcessor();

	private MagmaCompiler() {
	}

	@Override
	public <T> List<T> compile(Source source, Target<T> target) throws IOException, CompileException {
		var scripts = source.list();
		if (scripts.isEmpty()) {
			throw new CompileException("No scripts were found for source: " + source);
		}
		var intermediates = new ArrayList<T>();
		for (Script script : scripts) {
			Supplier<IOException> doesNotExist = () -> new IOException(script + " does not exist to be read.");
			var input = source.read(script).orElseThrow(doesNotExist);
			var output = compile(script, input);
			var intermediate = target.write(script, output);
			intermediates.add(intermediate);
		}
		return intermediates;
	}

	@Override
	public String compile(Script script, String content) throws CompileException {
		var lines = BracketSplitter_.split(content);
		var nodes = new ArrayList<Node>();
		for (String line : lines) {
			nodes.add(TokenizationStage_.apply(line));
		}
		if (nodes.isEmpty()) throw TokenizationException("No nodes were found.");
		var newList = new ArrayList<Node>();
		for (Node node : nodes) {
			newList.add(process(script, node));
		}
		var cache = new Cache<>(new EnumMap<>(Type.class));
		for (Node node : newList) {
			cache = attach(cache, node);
		}
		return cache.render();
	}

	private Node process(Script current, Node node) throws ProcessException {
		Node newNode;
		if (node.is(Node.Group.Import)) {
			newNode = scriptProcessor.process(current, node);
		} else if (node.is(Node.Group.Function)) {
			var identity = node.findIdentity().orElseThrow(() -> new ProcessException(node + " was a function but didn't have an identity."));
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
		return newNode.mapByChildrenExceptionally(node1 -> process(current, node1));
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