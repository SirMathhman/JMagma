package com.meti.exec.compile.render;

import static com.meti.exec.compile.render.RenderException.RenderException;

public class ContentType implements Type {
	private String content;

	private ContentType(String content) {
		this.content = content;
	}

	public static ContentType ContentType(String content) {
		return new ContentType(content);
	}

	@Override
	public String render(String name) throws RenderException {
		throw RenderException("Cannot render types with content.");
	}
}
