package com.meti.compile.stage;

import com.meti.compile.script.Result;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;

import java.util.List;

public interface RenderStage<C, G> {
	Result<C, G> render(Script script, List<Node> newList);
}
