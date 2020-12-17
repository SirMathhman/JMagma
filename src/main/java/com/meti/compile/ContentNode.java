package com.meti.compile;

public class ContentNode implements Node {
	private final String content;

	private ContentNode(String content) {
		this.content = content;
	}

	public static ContentNode ContentNode(String content) {
		return new ContentNode(content);
	}

	@Override
	public String render() {
		return content;
	}
}
