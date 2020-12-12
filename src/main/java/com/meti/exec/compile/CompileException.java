package com.meti.exec.compile;

public class CompileException extends Exception {
	private CompileException(String message) {
		super(message);
	}

	public static CompileException CompileException(String message) {
		return new CompileException(message);
	}
}
