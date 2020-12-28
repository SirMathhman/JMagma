package com.meti.compile;

import com.meti.compile.feature.Node;

import java.util.List;

public interface RenderStage {
	Result<TargetType> render(Script script, List<Node> newList);
}
