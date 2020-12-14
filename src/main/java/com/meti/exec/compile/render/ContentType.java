package com.meti.exec.compile.render;

import com.meti.api.core.Option;

public class ContentType implements Type {
	private String content;

	private ContentType(String content) {
		this.content = content;
	}

	public static ContentType ContentType(String content) {
		return new ContentType(content);
	}

	@Override
	public Option<String> render(String name) {
		return null;
	}

	@Override
	public Option<String> findContent() {
		return null;
	}

	@Override
	public Group findGroup() {
		return null;
	}

	@Override
	public Option<String> render() {
		return null;
	}
}
