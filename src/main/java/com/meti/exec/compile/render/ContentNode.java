package com.meti.exec.compile.render;

import com.meti.api.core.Option;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class ContentNode implements Node {
	private final String content;

	private ContentNode(String content) {
		this.content = content;
	}

	public static ContentNode ContentNode(String content) {
		return new ContentNode(content);
	}

	@Override
	public Option<String> render() {
		return None();
	}

	@Override
	public Option<String> findContent() {
		return Some(content);
	}
}
