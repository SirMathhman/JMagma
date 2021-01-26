package com.meti.compile;

public interface Compiler {
	String compileField(String line);

	String compileLines(String content);

	String compileNode(String line);

	String compileType(String content);
}
