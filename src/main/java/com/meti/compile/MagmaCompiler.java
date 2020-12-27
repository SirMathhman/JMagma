package com.meti.compile;

import com.meti.api.core.Supplier;
import com.meti.api.io.File;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.extern.Directive;
import com.meti.compile.feature.extern.ImportProcessor;
import com.meti.compile.feature.extern.ScriptProcessor;
import com.meti.compile.process.ProcessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

import static com.meti.compile.BracketSplitter.BracketSplitter_;
import static com.meti.compile.TokenizationException.TokenizationException;
import static com.meti.compile.TokenizationStage.TokenizationStage_;
import static com.meti.compile.feature.EmptyNode.EmptyNode_;
import static com.meti.compile.feature.Line.Line;
import static com.meti.compile.feature.Node.Group.*;

public class MagmaCompiler implements Compiler<TargetType, File> {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();
	private final ScriptProcessor scriptProcessor = new ImportProcessor();

	private MagmaCompiler() {
	}

	@Override
	public List<File> compile(Source source, Target<TargetType, File> target) throws IOException, CompileException {
		var scripts = source.list();
		if (scripts.isEmpty()) {
			throw new CompileException("No scripts were found for source: " + source);
		}
		var intermediates = new ArrayList<File>();
		for (Script script : scripts) {
			Supplier<IOException> doesNotExist = () -> new IOException(script + " does not exist to be read.");
			var input = source.read(script).orElseThrow(doesNotExist);
			var result = compileContent(script, input);
			var intermediate = target.write(script, result);
			intermediates.addAll(intermediate);
		}
		return intermediates;
	}

	private Result<TargetType> compileContent(Script script, String content) throws TokenizationException, ProcessException {
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
		var map = new HashMap<TargetType, Cache<CodeType>>();
		map.put(CTargetType.Source, new Cache<>(new EnumMap<>(CodeType.class)));
		map.put(CTargetType.Header, new Cache<>(new EnumMap<>(CodeType.class)));
		for (Node node : newList) {
			if (node.is(Import)) {
				map.get(CTargetType.Header).put(CodeType.Include, node);
			} else if (node.is(Structure)) {
				map.get(CTargetType.Source).put(CodeType.Structure, node);
			} else if (node.is(Function)) {
				map.get(CTargetType.Source).put(CodeType.Function, node);
			} else {
				map.get(CTargetType.Source).put(CodeType.Other, node);
			}
		}
		map.get(CTargetType.Source).put(CodeType.Include, Directive.Include.toNode("\"" + script.name() + ".h\""));
		return new MapResult<>(map);
	}

	private Node process(Script current, Node node) throws ProcessException {
		Node newNode;
		if (node.is(Node.Group.Import)) {
			newNode = scriptProcessor.process(current, node);
		} else if (node.is(Node.Group.Function)) {
			var identity = node.findIdentity().orElseThrow(() -> new ProcessException(node + " was a function but didn't have an identity."));
			if (identity.isFlagged(com.meti.compile.feature.field.Field.Flag.NATIVE)) {
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

	enum CTargetType implements TargetType {
		Source("%s.c"),
		Header("%s.h");

		public final String format;

		CTargetType(String format) {
			this.format = format;
		}

		@Override
		public String format(String name) {
			return format.formatted(name);
		}
	}

	enum CodeType {
		Structure,
		Function,
		Include, Other
	}
}