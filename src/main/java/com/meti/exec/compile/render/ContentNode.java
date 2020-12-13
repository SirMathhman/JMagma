package com.meti.exec.compile.render;

public class ContentNode implements Node<ContentNode> {
	private final String content;

	private ContentNode(String content) {
		this.content = content;
	}

	public static ContentNode ContentNode(String content) {
		return new ContentNode(content);
	}

	@Override
	public String render() throws RenderException {
		return null;
	}

	@Override
	public int compareTo(ContentNode o) {
		return 0;
	}
}
