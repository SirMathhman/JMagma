package com.meti.exec.compile.render;

import com.meti.api.core.None;
import com.meti.api.core.Option;

import static com.meti.api.core.None.None;

public class ContentNode implements Node<ContentNode> {
	private final String content;

	private ContentNode(String content) {
		this.content = content;
	}

	public static ContentNode ContentNode(String content) {
		return new ContentNode(content);
	}

	@Override
	public int compareTo(ContentNode o) {
		return 0;
	}

	@Override
	public Option<String> render() {
		return None();
	}
}
