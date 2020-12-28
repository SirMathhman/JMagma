package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.List;

public interface RenderStage<C, G> {
	Result<C, G> render(Script script, List<Node> newList);
}
