package com.meti.compile;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.extern.Directive;

import java.util.EnumMap;
import java.util.List;

import static com.meti.compile.feature.Node.Group.*;

public class CRenderStage implements RenderStage<CRenderStage.CClass, CRenderStage.CGroup> {
	static final CRenderStage CRenderStage_ = new CRenderStage();

	private CRenderStage() {
	}

	@Override
	public Result<CRenderStage.CClass, CGroup> render(Script script, List<Node> newList) {
		var result = new MapResult<>(new EnumMap<CClass, Cache<CGroup>>(CClass.class));
		for (Node node : newList) {
			extracted(result, node);
		}
		result.put(Directive.Include.toNode("\"" + script.name() + ".h\""), CClass.Source, CGroup.Include);
		return result;
	}

	private void extracted(MapResult<CClass, CGroup> result, Node node) {
		if (node.is(Import)) {
			result.put(node, CClass.Header, CGroup.Include);
		} else if (node.is(Structure)) {
			result.put(node, CClass.Source, CGroup.Structure);
		} else if (node.is(Function)) {
			result.put(node, CClass.Source, CGroup.Function);
		} else {
			result.put(node, CClass.Source, CGroup.Other);
		}
	}

	enum CClass implements Class {
		Source("%value.c"),
		Header("%value.h");

		public final String format;

		CClass(String format) {
			this.format = format;
		}

		@Override
		public String format(String name) {
			return format.formatted(name);
		}
	}

	enum CGroup {
		Structure,
		Function,
		Include, Other
	}
}