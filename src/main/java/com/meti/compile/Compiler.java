package com.meti.compile;

public class Compiler {
	String compile(String content) throws CompileException {
		try {
			var value = Integer.parseInt(content);
			return String.valueOf(value);
		} catch (NumberFormatException e) {
			throw new CompileException("Unable to compile content: " + content);
		}
	}
}