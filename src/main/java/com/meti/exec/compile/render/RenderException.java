package com.meti.exec.compile.render;

import com.meti.exec.compile.CompileException;

public class RenderException extends CompileException {
	private RenderException(String message) {
		super(message);
	}

	protected static RenderException RenderException(String message) {
		return new RenderException(message);
	}
}
