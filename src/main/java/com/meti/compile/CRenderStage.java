package com.meti.compile;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.extern.Directive;

import java.util.HashMap;
import java.util.List;

import static com.meti.compile.feature.Node.Group.*;

public class CRenderStage implements RenderStage {
	static final CRenderStage CRenderStage_ = new CRenderStage();

	private CRenderStage() {
	}

	@Override
	public Result<TargetType> render(Script script, List<Node> newList) {
		var map = new HashMap<TargetType, Cache<CodeType>>();
		var result = new MapResult<>(map);
		for (Node node : newList) {
			extracted(result, node);
		}
		result.put(Directive.Include.toNode("\"" + script.name() + ".h\""), CTargetType.Source, CodeType.Include);
		return result;
	}

	private void extracted(MapResult<TargetType, CodeType> result, Node node) {
		if (node.is(Import)) {
			result.put(node, CTargetType.Header, CodeType.Include);
		} else if (node.is(Structure)) {
			result.put(node, CTargetType.Source, CodeType.Structure);
		} else if (node.is(Function)) {
			result.put(node, CTargetType.Source, CodeType.Function);
		} else {
			result.put(node, CTargetType.Source, CodeType.Other);
		}
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