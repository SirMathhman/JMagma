package com.meti.exec.compile.render;

import com.meti.api.collect.string.JSONNode;
import com.meti.api.core.Option;
import com.meti.api.core.Stringable;

public interface Renderable extends Stringable {
	default JSONNode asJson() {
		return JSONNode.Empty;
	}

	Option<String> render();
}
