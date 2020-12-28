package com.meti.compile.stage;

import com.meti.compile.token.Node;
import com.meti.compile.script.MapResult;
import com.meti.compile.script.Result;
import com.meti.compile.script.Script;

import java.util.EnumMap;
import java.util.List;

import static com.meti.compile.token.Node.Group.Function;
import static com.meti.compile.token.Node.Group.Structure;
import static com.meti.compile.feature.extern.Directives.Include;

public class CRenderStage implements RenderStage<CClass, CGroup> {
	public static final CRenderStage CRenderStage_ = new CRenderStage();

	private CRenderStage() {
	}

	@Override
	public Result<CClass, CGroup> render(Script script, List<Node> newList) {
		Result<CClass, CGroup> result = new MapResult<>(new EnumMap<>(CClass.class));
		for (Node node : newList) {
			result = extracted(result, node);
		}
		if (result.has(CClass.Header)) {
			return result.put(CClass.Source, CGroup.Directive, Include.toNode("\"" + script.name() + ".h\""));
		} else {
			return result;
		}
	}

	private Result<CClass, CGroup> extracted(Result<CClass, CGroup> result, Node node) {
		if (node.is(Node.Group.Directive)) {
			return result.put(CClass.Header, CGroup.Directive, node);
		} else if (node.is(Structure)) {
			return result.put(CClass.Source, CGroup.Structure, node);
		} else if (node.is(Function)) {
			return result.put(CClass.Source, CGroup.Function, node);
		} else {
			return result.put(CClass.Source, CGroup.Other, node);
		}
	}
}