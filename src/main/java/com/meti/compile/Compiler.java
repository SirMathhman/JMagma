package com.meti.compile;

public class Compiler {
	public static String compile(String value) {
		return "#include <stdio.h>\nint main(){printf(\"Hello World!\");return 0;}";
	}
}
