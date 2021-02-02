package com.meti.compile.token;

public class Input {
	private final String content;

	public Input(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public boolean hasContent() {
		return !getContent().isBlank();
	}

	public Input trim() {
		return new Input(getContent().trim());
	}
}
