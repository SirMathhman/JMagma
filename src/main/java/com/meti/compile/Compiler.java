package com.meti.compile;

public class Compiler {
	String compile(String content) {
		try {
			var value = Integer.parseInt(content);
			return String.valueOf(value);
		} catch (NumberFormatException e) {
			return "int main(){return 0;}";
		}
	}
}