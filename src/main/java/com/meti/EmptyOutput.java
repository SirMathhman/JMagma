package com.meti;

public class EmptyOutput implements Output {
	public static final Output EmptyOutput_ = new EmptyOutput();

	public EmptyOutput() {
	}

	@Override
	public String render() {
		return "";
	}

	@Override
	public Output concat(Renderable other) {
		return new RenderableOutput(other);
	}

	@Override
	public Output concat(String other) {
		return new StringOutput(other);
	}
}
