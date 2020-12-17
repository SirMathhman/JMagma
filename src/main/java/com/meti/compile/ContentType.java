package com.meti.compile;

public class ContentType implements Type {
	private final String content;

	private ContentType(String content) {
		this.content = content;
	}

	public static ContentType ContentType(String content) {
		return new ContentType(content);
	}

	@Override
	public String render(String value) {
		return content + " " + value;
	}
}
